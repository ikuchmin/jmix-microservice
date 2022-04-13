package ru.udya.services.adminjmix.security.auth.config;

import io.jmix.oidc.claimsmapper.ClaimsRolesMapper;
import io.jmix.oidc.userinfo.JmixOidcUserService;
import io.jmix.oidc.usermapper.OidcUserMapper;
import io.jmix.security.role.ResourceRoleRepository;
import io.jmix.security.role.RowLevelRoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.*;
import ru.udya.services.adminjmix.security.auth.claimsmapper.KeycloakClaimsRolesMapper;
import ru.udya.services.adminjmix.security.auth.userinfo.ExtJmixOidcUserService;

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
    JmixOidcUserService jmixOidcUserService(OidcUserMapper oidcUserMapper, JwtDecoder jwtDecoder) {
        return new ExtJmixOidcUserService(oidcUserMapper, jwtDecoder);
    }

    @Bean
    @ConditionalOnBean(ResourceRoleRepository.class)
    ClaimsRolesMapper claimsRoleMapper(ResourceRoleRepository resourceRoleRepository,
                                              RowLevelRoleRepository rowLevelRoleRepository) {

        var mapper = new KeycloakClaimsRolesMapper(resourceRoleRepository, rowLevelRoleRepository);
        mapper.setResourceId(resourceId);

        return mapper;
    }
}
