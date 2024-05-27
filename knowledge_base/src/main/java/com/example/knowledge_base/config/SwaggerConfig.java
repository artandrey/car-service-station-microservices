package com.example.knowledge_base.config;

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
                                .info(new Info().title("Knowledge base service")
                                                .description("Knowledge base that contains documents with information about car services, that is used by AI to answer")
                                                .version("1.0"))
                                .components(new Components().addSecuritySchemes("Bearer Authentication",
                                                createAPIKeyScheme()))
                                .addServersItem(new Server().url(gatewayUrl));
        }
}
