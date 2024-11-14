package com.pitang.user.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Classe de configuração de segurança para o projeto users.
 * 
 * Essa classe define as configurações e disponibiliza as implementações de
 * algumas interfaces.
 *
 * @author Carlos Pereira
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * Método responsável por desabilitar a segurança padrão do spring security no
	 * microsserviço users, devido ao gerenciador acesso já tratar toda a questão de
	 * controle de acesso.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param http
	 * @return SecurityFilterChain
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.anyRequest().permitAll()).build();
	}

	/**
	 * Método responsável por disponibilizar uma implementação para criptografia das
	 * senhas.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @return PasswordEncoder
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
