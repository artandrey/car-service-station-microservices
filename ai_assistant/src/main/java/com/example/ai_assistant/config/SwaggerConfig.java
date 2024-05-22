package com.example.ai_assistant.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
        @Value("${gateway.url}")
        private String gatewayUrl;

        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()
                                .info(new Info().title("AI assistant service")
                                                .description("Chat with AI to get useful information about car service")
                                                .version("1.0"))
                                .addServersItem(new Server().url(gatewayUrl));
        }
}
