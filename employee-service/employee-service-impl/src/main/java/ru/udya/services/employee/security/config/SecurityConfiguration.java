package ru.udya.services.employee.security.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.SupplierJwtDecoder;

import java.util.Collections;
import java.util.Optional;

import static io.jmix.security.SecurityConfigurers.apiSecurity;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final OAuth2ResourceServerProperties.Jwt jwtProperties;

    SecurityConfiguration(OAuth2ResourceServerProperties properties) {
        this.jwtProperties = properties.getJwt();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .apply(apiSecurity()).and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

    @Bean
    SupplierJwtDecoder supplierJwtDecoder() {
        return new SupplierJwtDecoder(this::jwtDecoder);
    }

    // Using custom jwtDecoder to avoid valida issuer. It is different
    // for the jwt getting through gateway and openid configuration
    // getting from auth service
    private JwtDecoder jwtDecoder() {
        var jwtDecoder = (NimbusJwtDecoder) JwtDecoders
                .fromIssuerLocation(this.jwtProperties.getIssuerUri());

        jwtDecoder.setJwtValidator(JwtValidators.createDefault());

        return jwtDecoder;
    }

    @Bean
    public JwtUserDetailsAuthenticationConverter keycloakJwtAuthenticationConverter() {
        JwtUserDetailsAuthenticationConverter converter = new JwtUserDetailsAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt ->
                Optional.ofNullable(jwt.getClaimAsStringList("roles"))
                        .map(keycloakRolesMapper::createAuthorities)
                        .orElse(Collections.emptyList())
        );
        return converter;
    }
}