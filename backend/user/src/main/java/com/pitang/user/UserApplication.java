package com.pitang.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.pitang.user.entities.User;

/**
 * Classe principal que inicializa a aplicação Spring Boot do micro serviço de
 * {@link User}.
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.pitang.common.proxies")
@ComponentScan(basePackages = { "com.pitang.user", "com.pitang.common.utils" })
public class UserApplication {

	/**
	 * Método principal que inicializa a aplicação Spring Boot do micro serviço de
	 * {@link User}.
	 * 
	 * @param args Argumentos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
