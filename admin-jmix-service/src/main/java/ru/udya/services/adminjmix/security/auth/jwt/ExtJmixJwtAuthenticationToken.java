package ru.udya.services.adminjmix.security.auth.jwt;

import io.jmix.oidc.jwt.JmixJwtAuthenticationToken;
import io.jmix.oidc.user.JmixOidcUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

public class ExtJmixJwtAuthenticationToken extends JmixJwtAuthenticationToken {

    /**
     * Constructs a {@code JmixJwtAuthenticationToken} using the provided parameters.
     *
     * @param jwt         the JWT
     * @param principal   the principal being authenticated
     * @param authorities the authorities assigned to the JWT
     */
    public ExtJmixJwtAuthenticationToken(Jwt jwt,
                                         JmixOidcUser principal,
                                         Collection<? extends GrantedAuthority> authorities) {
        super(jwt, principal, authorities);
    }

    // begin extension
    public final Jwt getToken() {
        return this.token;
    }
    // end extension
}
