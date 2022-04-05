package ru.udya.services.department.auth.config;

import io.jmix.oidc.jwt.JmixJwtAuthenticationConverter;
import io.jmix.oidc.usermapper.OidcUserMapper;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.SupplierJwtDecoder;
import ru.udya.services.department.auth.jwt.ExtJmixJwtAuthenticationConverter;

@Configuration(proxyBeanMethods = false)
public class JwtConfiguration {

    private final OAuth2ResourceServerProperties.Jwt jwtProperties;

    JwtConfiguration(OAuth2ResourceServerProperties properties) {
        this.jwtProperties = properties.getJwt();
    }

    @Bean
    public JmixJwtAuthenticationConverter jmixJwtAuthenticationConverter(OidcUserMapper oidcUserMapper) {
        return new ExtJmixJwtAuthenticationConverter(oidcUserMapper);
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
}
