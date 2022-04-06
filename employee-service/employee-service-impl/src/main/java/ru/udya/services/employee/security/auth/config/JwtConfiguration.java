package ru.udya.services.employee.security.auth.config;

import io.jmix.oidc.OidcProperties;
import io.jmix.oidc.claimsmapper.ClaimsRolesMapper;
import io.jmix.oidc.claimsmapper.DefaultClaimsRolesMapper;
import io.jmix.security.role.ResourceRoleRepository;
import io.jmix.security.role.RowLevelRoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.SupplierJwtDecoder;
import ru.udya.services.employee.security.auth.claimsmapper.KeycloakClaimsRolesMapper;

@Configuration(proxyBeanMethods = false)
public class JwtConfiguration {

    @Value("${spring.security.oauth2.resourceserver.id:}")
    protected String resourceId;

    private final OAuth2ResourceServerProperties.Jwt jwtProperties;

    JwtConfiguration(OAuth2ResourceServerProperties properties) {
        this.jwtProperties = properties.getJwt();
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
    @ConditionalOnBean(ResourceRoleRepository.class)
    public ClaimsRolesMapper claimsRoleMapper(ResourceRoleRepository resourceRoleRepository,
                                              RowLevelRoleRepository rowLevelRoleRepository) {

        var mapper = new KeycloakClaimsRolesMapper(resourceRoleRepository, rowLevelRoleRepository);
        mapper.setResourceId(resourceId);

        return mapper;
    }
}