package com.example.payment_service.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
        @Value("${gateway.url}")
        private String gatewayUrl;

        private SecurityScheme createAPIKeyScheme() {
                return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer");
        }

        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()
                                .info(new Info().title("Payment service")
                                                .description("This service responsible for bills operations")
                                                .version("1.0"))
                                .components(new Components().addSecuritySchemes("Bearer Authentication",
                                                createAPIKeyScheme()))
                                .addServersItem(new Server().url(gatewayUrl));
        }
}
