package com.pitang.authenticationManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Classe principal que inicializa a aplicação Spring Boot do serviço de
 * gerenciamento de autenticação.
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.pitang.common.proxies")
@ComponentScan(basePackages = { "com.pitang.authenticationManager", "com.pitang.common.utils" })
public class AuthenticationManagerApplication {

	/**
	 * Método principal que inicializa a aplicação Spring Boot do serviço de
	 * gerenciamento de autenticação.
	 * 
	 * @param args Argumentos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationManagerApplication.class, args);
	}

}
