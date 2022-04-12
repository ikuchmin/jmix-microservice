package ru.udya.services.adminjmix.security.auth.userinfo;

import io.jmix.oidc.user.JmixOidcUser;
import io.jmix.oidc.userinfo.DefaultJmixOidcUserService;
import io.jmix.oidc.userinfo.JmixOidcUserService;
import io.jmix.oidc.usermapper.OidcUserMapper;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

public class ExtJmixOidcUserService extends DefaultJmixOidcUserService
        implements JmixOidcUserService {

    private JwtDecoder jwtDecoder;

    public ExtJmixOidcUserService(OidcUserMapper<? extends JmixOidcUser> userMapper,
                                  JwtDecoder jwtDecoder) {
        super(userMapper);
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUserService delegate = new OidcUserService();
        OidcUser oidcUser = delegate.loadUser(userRequest);

        // inspired by JmixJwtAuthenticationConverter
        // this is a hack to force jmix extracting roles from access_token instead of id_token
        var accessToken = jwtDecoder.decode(userRequest.getAccessToken().getTokenValue());
        OidcIdToken overriddenIdToken = OidcIdToken.withTokenValue(accessToken.getTokenValue())
                .claims(claims -> claims.putAll(oidcUser.getClaims()))
                .claims(claims -> claims.putAll(accessToken.getClaims()))
                .build();

        var overriddenOidcUser = new DefaultOidcUser(oidcUser.getAuthorities(),
                overriddenIdToken, oidcUser.getUserInfo());

        return obtainJmixOidcUser(overriddenOidcUser);
    }
}
