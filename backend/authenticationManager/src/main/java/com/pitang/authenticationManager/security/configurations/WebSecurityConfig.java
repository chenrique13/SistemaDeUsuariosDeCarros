package com.pitang.authenticationManager.security.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.FormLoginSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import com.pitang.authenticationManager.security.jwt.JwtAuthenticationEntryPoint;
import com.pitang.common.proxies.UserProxy;
import com.pitang.common.utils.JwtUtils;
import com.pitang.common.utils.RoutesCommon;

/**
 * Classe de configuração para relalizar o controle de acesso utilizando o
 * spring security.
 */
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserProxy userProxy;

	/**
	 * Realiza o controle de acesso as rotas, adicionando filtros para verificação
	 * do token, liberação de rotas públicas e demais configurações para o melhor
	 * funcionamento do sistema.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param http
	 * @return SecurityWebFilterChain
	 */
	@Bean
	SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		final String USER_ROLE = "USER";

		http.csrf(ServerHttpSecurity.CsrfSpec::disable);
		
		http.cors(corsSpec -> corsSpec.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedOrigin("http://localhost:4200");
            config.addAllowedMethod("*");
            config.addAllowedHeader("*");
            config.addExposedHeader("Authorization");
            config.setAllowCredentials(true);
            return config;
        }));

		http.authorizeExchange(spec -> {
            spec.pathMatchers(HttpMethod.OPTIONS).permitAll();
            
            spec.pathMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll();
            
			RoutesCommon.PUBLIC_ROUTES.forEach((route, methodsMap) -> {
				methodsMap.forEach((service, methods) -> {
					for (HttpMethod method : methods) {
						spec.pathMatchers(method,route).permitAll();
					}
				});
			});
			RoutesCommon.PRIVATE_ROUTES.forEach((route, methodsMap) -> {
				methodsMap.forEach((service, methods) -> {
					for (HttpMethod method : methods) {
						spec.pathMatchers(method, route).hasAnyRole(USER_ROLE);
					}
				});
			});
			spec.anyExchange().authenticated();
		});

		http.addFilterBefore(new JwtAuthenticationEntryPoint(jwtUtils, userProxy),
				SecurityWebFiltersOrder.AUTHENTICATION);

		http.httpBasic(HttpBasicSpec::disable);
		http.formLogin(FormLoginSpec::disable);

		return http.build();
	}

}
