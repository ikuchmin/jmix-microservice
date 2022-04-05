package ru.udya.services.department.auth.jwt;

import io.jmix.oidc.jwt.JmixJwtAuthenticationConverter;
import io.jmix.oidc.jwt.JmixJwtAuthenticationToken;
import io.jmix.oidc.user.JmixOidcUser;
import io.jmix.oidc.usermapper.OidcUserMapper;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collections;

public class ExtJmixJwtAuthenticationConverter extends JmixJwtAuthenticationConverter {

    public ExtJmixJwtAuthenticationConverter(OidcUserMapper oidcUserMapper) {
        super(oidcUserMapper);
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        OidcIdToken oidcIdToken = OidcIdToken.withTokenValue(jwt.getTokenValue())
                .claims(claims -> claims.putAll(jwt.getClaims()))
                .build();
        DefaultOidcUser oidcUser = new DefaultOidcUser(Collections.emptyList(), oidcIdToken, usernameClaimName);
        JmixOidcUser jmixOidcUser = oidcUserMapper.toJmixUser(oidcUser);
        JmixJwtAuthenticationToken token = new ExtJmixJwtAuthenticationToken(jwt,
                jmixOidcUser,
                jmixOidcUser.getAuthorities());
        return token;
    }


}
