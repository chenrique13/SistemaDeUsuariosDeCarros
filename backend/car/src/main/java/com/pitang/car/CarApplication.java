package com.pitang.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Classe principal que inicializa a aplicação Spring Boot do micro serviço de
 * carros.
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.pitang.common.proxies")
@ComponentScan(basePackages = { "com.pitang.car", "com.pitang.common.utils" })
public class CarApplication {

	/**
	 * Classe principal que inicializa a aplicação Spring Boot do micro serviço de
	 * carros.
	 * 
	 * @param args Argumentos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CarApplication.class, args);
	}

}
