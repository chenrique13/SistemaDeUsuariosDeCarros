package com.pitang.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal que inicializa a aplicação Spring Boot do projeto comum que
 * auxilia nos micros serviços.
 */
@SpringBootApplication
public class CommonApplication {

	/**
	 * Método principal que inicializa a aplicação Spring Boot do projeto comum que
	 * auxilia nos micros serviços.
	 * 
	 * @param args Argumentos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}

}
