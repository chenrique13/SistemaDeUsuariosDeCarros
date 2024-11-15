package com.pitang.car.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Classe de configuração da documentação do micro serviço de carros.
 */
@Configuration
public class OpenApiConfig {

	  @Bean
	    OpenAPI cutomizacaoOpenAPI() {
	        return new OpenAPI()
	                .info(new Info().title("Micro serviço de carros.")
	                        .description("Documentação da API Controle do micro serviço de carros.")
	                        .version("v1.0"))
	                .externalDocs(new ExternalDocumentation()
	                        .description("Mais detalhes")
	                        .url("https://github.com/chenrique13/SistemaDeUsuariosDeCarros"));
	    }
	
}
