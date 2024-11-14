package com.pitang.authenticationManager.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.pitang.common.exceptions.CustomException;
import com.pitang.common.proxies.UserProxy;
import com.pitang.common.utils.JwtUtils;
import com.pitang.common.utils.RoutesCommon;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Classe responsável por ser a porta de entrada para comunicação e autenticação
 * dos serviços.
 */
@Component
public class JwtAuthenticationEntryPoint implements WebFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserProxy userProxy;

	/**
	 * Construtor com todos os argumentos.
	 * 
	 * @param jwtComumServico
	 * @param userProxy
	 */
	public JwtAuthenticationEntryPoint(JwtUtils jwtUtils, UserProxy userProxy) {
		this.jwtUtils = jwtUtils;
		this.userProxy = userProxy;
	}

	/**
	 * Método que contém toda a regra de negócio para verificar o token, adicionar
	 * um usuário ao contexto do spring security e trata para gerar os erros
	 * possiveis.
	 * 
	 * @author Carlos Pereira
	 * 
	 * @param exchange
	 * @param chain
	 * @return Mono Void
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		String route = exchange.getRequest().getURI().getPath();
		HttpMethod httpMethod = exchange.getRequest().getMethod();

		if (httpMethod == HttpMethod.OPTIONS) {
			return chain.filter(exchange);
		}

		if (RoutesCommon.isRoutePrivate(route, httpMethod)) {
			try {
				String token = jwtUtils
						.validateRecoverToken(exchange.getRequest().getHeaders().getFirst("Authorization"));

				if (token != null) {
					String login = jwtUtils.getLogin(token);
					return Mono.fromCallable(() -> userProxy.findUserByLogin(login))
							.subscribeOn(Schedulers.boundedElastic()).flatMap(userDTO -> {
								if (userDTO != null) {
									Authentication authentication = new UsernamePasswordAuthenticationToken(
											userDTO.getLogin(), null,
											Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
									SecurityContext securityContext = new SecurityContextImpl(authentication);
									return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder
											.withSecurityContext(Mono.just(securityContext)));
								} else {
									return Mono.error(new CustomException("Unauthorized", 1, HttpStatus.UNAUTHORIZED));
								}
							}).onErrorResume(
									e -> createStandardError(exchange, HttpStatus.UNAUTHORIZED, e.getMessage(), 1));
				} else {
					return createStandardError(exchange, HttpStatus.UNAUTHORIZED, "Unauthorized", 1);
				}
			} catch (ExpiredJwtException e) {
				return createStandardError(exchange, HttpStatus.UNAUTHORIZED, "Unauthorized - invalid session", 2);
			} catch (SignatureException e) {
				return createStandardError(exchange, HttpStatus.UNAUTHORIZED, "Unauthorized", 1);
			} catch (NullPointerException e) {
				return createStandardError(exchange, HttpStatus.UNAUTHORIZED, "Unauthorized", 1);
			} catch (Exception e) {
				return createStandardError(exchange, HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error",
						HttpStatus.INTERNAL_SERVER_ERROR.value());
			}
		} else {
			return chain.filter(exchange);
		}
	}

	/**
	 * Método responsável por retornar um erro padrão quando existir falha no
	 * processo de autenticação/autorização.
	 *
	 * @author Carlos Pereira
	 *
	 * @param exchange
	 * @param status
	 * @param message
	 * @param erroCode
	 * @return Mono Void
	 */
	private Mono<Void> createStandardError(ServerWebExchange exchange, HttpStatus status, String message,
			int erroCode) {
		CustomException customException = new CustomException(message, erroCode, status);

		exchange.getResponse().setStatusCode(status);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

		DataBuffer dataBuffer = exchange.getResponse().bufferFactory()
				.wrap(customException.toJSON().getBytes(StandardCharsets.UTF_8));

		return exchange.getResponse().writeWith(Mono.just(dataBuffer));
	}
}
