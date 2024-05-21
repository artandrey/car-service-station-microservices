package com.example.api_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

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
                // allowing requests from all domains
                // .cors(cors -> cors.configurationSource(request -> {
                // var corsConfiguration = new CorsConfiguration();
                // corsConfiguration.setAllowedOriginPatterns(List.of("*"));
                // corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE",
                // "OPTIONS"));
                // corsConfiguration.setAllowedHeaders(List.of("*"));
                // corsConfiguration.setAllowCredentials(true);
                // return corsConfiguration;
                // }))
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/auth/v1/*").permitAll()
                        .pathMatchers("/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**", "/h2-console/**")
                        .permitAll()
                        // for working with keycloak auth
                        .anyExchange().authenticated()
                // for working without a keycloak connection
                // .anyRequest().permitAll()

                )

                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwkSetUri(issUrl)))
                // .sessionManagement(sess ->
                // sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
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