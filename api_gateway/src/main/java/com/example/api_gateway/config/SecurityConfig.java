package com.example.api_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String issUrl;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/auth/*").permitAll()
                        .pathMatchers("/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**")
                        .permitAll().pathMatchers("*/v3/api-docs/**", "*/swagger-ui/**", "*/swagger-resources/*")
                        .permitAll()
                        // for working with keycloak auth
                        .anyExchange().authenticated()
                // for working without a keycloak connection
                // .anyRequest().permitAll()

                )

                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwkSetUri(issUrl)))
                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        converter.setPrincipalClaimName("preferred_username");

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = jwtGrantedAuthoritiesConverter.convert(jwt);
            List<String> roles = (List<String>) jwt.getClaimAsMap("realm_access").get("roles");

            return Stream.concat(authorities.stream(),
                    roles.stream()
                            .filter(role -> role.startsWith("ROLE_"))
                            .map(SimpleGrantedAuthority::new)
                            .map(GrantedAuthority.class::cast))
                    .toList();
        });

        return converter;
    }
}