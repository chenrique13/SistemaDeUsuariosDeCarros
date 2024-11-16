package com.pitang.service_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Classe principal que inicializa a aplicação Spring Boot do serviço de
 * registros.
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	/**
	 * Método principal que inicializa a aplicação Spring Boot do serviço de
	 * registros.
	 * 
	 * @param args Argumentos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}
