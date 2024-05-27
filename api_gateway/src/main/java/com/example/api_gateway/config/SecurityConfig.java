package com.example.api_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String issUrl;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/auth/*").permitAll()
                        .pathMatchers("/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**").permitAll()
                        .pathMatchers("*/v3/api-docs/**", "*/swagger-ui/**", "*/swagger-resources/*").permitAll()
                        // AI Assistant access
                        .pathMatchers("/api/v1/ai/**").authenticated()
                        // Knowledge base access
                        .pathMatchers("/api/v1/knowledge-base/**").hasRole(AppRoles.MANAGER.name())
                        // Management service access
                        .pathMatchers("/api/v1/management/public/**").authenticated()
                        .pathMatchers("/api/v1/management/orders/**", "/api/v1/management/order-tasks/**",
                                "/api/v1/management/car-brands/**", "/api/v1/management/cars/**",
                                "/api/v1/management/car-models/**", "/api/v1/management/car-parts/**")
                        .hasRole(AppRoles.STAFF.name())
                        .pathMatchers("/api/v1/management/worker-positions/**", "/api/v1/management/worker-profiles/**")
                        .hasRole(AppRoles.MANAGER.name())
                        // Payment service access
                        .pathMatchers("/api/v1/payment/public/**").hasRole(AppRoles.CLIENT.name())
                        .pathMatchers("/api/v1/payment/**")
                        .hasAnyRole(AppRoles.MANAGER.name(), AppRoles.ACCOUNTANT.name())
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .jwkSetUri(issUrl)
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        converter.setPrincipalClaimName("preferred_username");

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = jwtGrantedAuthoritiesConverter.convert(jwt);
            Map<String, Object> resourceAccess = jwt.getClaimAsMap("resource_access");
            List<String> carServiceRoles = ((Map<String, List<String>>) resourceAccess.get("car-service")).get("roles");

            List<GrantedAuthority> customAuthorities = carServiceRoles.stream()
                    .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return Stream.concat(authorities.stream(), customAuthorities.stream())
                    .collect(Collectors.toList());
        });

        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }
}
