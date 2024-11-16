package com.pitang.common.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pitang.common.dtos.authentication.SigninDTO;

/**
 * É uma interface Feign que se comunica com um serviço de autenticação remoto
 * para realizar o login ou validar tokens.
 */
@FeignClient(name = "authenticationManeger-service")
public interface AuthenticationProxy {

	/**
	 * EndPoint responsável por fazer login no sistema.
	 * 
	 * @param signinDTO Objeto que representa o DTO do signin.
	 * @return {@link ResponseEntity} da operação foi concluída sem um retorno de
	 *         dados.
	 */
	@PostMapping(path = "/signin")
	public ResponseEntity<Void> signinUser(@RequestBody SigninDTO signinDTO);

}
