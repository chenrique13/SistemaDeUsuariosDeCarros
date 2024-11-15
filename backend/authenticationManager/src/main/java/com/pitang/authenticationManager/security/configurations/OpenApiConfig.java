package com.pitang.authenticationManager.security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    /**
     * Configuração de customização da documentação OpenAPI para o API Gateway.
     */
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Micro serviço de gerenciamento de autenticação")
                        .description("Documentação da gerenciador dos micros serviços.")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Mais detalhes")
                        .url("https://github.com/chenrique13/SistemaDeUsuariosDeCarros"));
    }
}