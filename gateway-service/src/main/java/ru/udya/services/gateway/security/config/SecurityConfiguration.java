package ru.udya.services.gateway.security.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ResourceServerSpec;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.oauth2.jwt.SupplierReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

    private final OAuth2ResourceServerProperties.Jwt jwtProperties;

    SecurityConfiguration(OAuth2ResourceServerProperties properties) {
        this.jwtProperties = properties.getJwt();
    }


    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange(ae -> ae
                        .pathMatchers("/auth/realms/**").permitAll()
                        .pathMatchers("/actuator/**").permitAll()
                        .anyExchange().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerSpec::jwt)
                .csrf(CsrfSpec::disable);

        return http.build();
    }

    @Bean
    SupplierReactiveJwtDecoder supplierJwtDecoder() {
        return new SupplierReactiveJwtDecoder(this::jwtDecoder);
    }

    // Using custom jwtDecoder to avoid valida issuer. It is different
    // for the jwt getting through gateway and openid configuration
    // getting from auth service
    private ReactiveJwtDecoder jwtDecoder() {
        var jwtDecoder = (NimbusReactiveJwtDecoder) ReactiveJwtDecoders
                .fromIssuerLocation(this.jwtProperties.getIssuerUri());

        jwtDecoder.setJwtValidator(JwtValidators.createDefault());

        return jwtDecoder;
    }
}
