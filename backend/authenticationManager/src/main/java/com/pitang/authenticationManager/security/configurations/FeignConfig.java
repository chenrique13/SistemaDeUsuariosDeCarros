package com.pitang.authenticationManager.security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

/**
 * Classe de configuração para o Feign.
 * 
 * Essa classe disponibiliza as implementações de algumas interfaces.
 *
 * @author Carlos Pereira
 */
@Configuration
public class FeignConfig {

	/**
	 * Disponibiliza um codificador para o Feign codificar corretamente os objetos.
	 * 
	 * @return Encoder
	 */
	@Bean
	Encoder encoder() {
		return new JacksonEncoder();
	}

	/**
	 * Disponibiliza um decodificador para o Feign decodificar corretamente os
	 * objetos.
	 * 
	 * @return Decoder
	 */
	@Bean
	Decoder decoder() {
		return new JacksonDecoder();
	}
	
}