package com.example.discovery_server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(new Customizer<CsrfConfigurer<HttpSecurity>>() {
            public void customize(CsrfConfigurer<HttpSecurity> t) {
                t.ignoringRequestMatchers("/eureka/**");
            };
        });
        return httpSecurity.build();
    }
}