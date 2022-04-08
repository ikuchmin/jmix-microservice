package ru.udya.services.organization.security.auth.converter;

import com.nimbusds.jose.shaded.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class KeycloakJwtGrantedRoleAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final Log logger = LogFactory.getLog(getClass());

    private static final String REALM_ACCESS_CLAIM = "realm_access";
    private static final String RESOURCE_ACCESS_CLAIM = "resource_access";
    private static final String ROLES_CLAIM = "roles";

    private static final String ROLE_AUTHORITY_PREFIX = "ROLE_";

    private String resourceId = "";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (String authority : getAuthorities(jwt)) {
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_AUTHORITY_PREFIX + authority));
        }
        return grantedAuthorities;
    }

    private Collection<String> getAuthorities(Jwt jwt) {
        return getRolesCodes(jwt.getClaims());
    }

    private Collection<String> getRolesCodes(Map<String, Object> claims) {
        var realmAccessRoles = Optional.ofNullable(claims.get(REALM_ACCESS_CLAIM))
                .map(rac -> ((JSONObject) rac).get(ROLES_CLAIM))
                .map(rc -> (Collection<String>) rc).orElse(emptyList());

        var resourceAccessRoles = Optional.ofNullable(claims.get(RESOURCE_ACCESS_CLAIM))
                .map(rac -> ((JSONObject) rac).get(resourceId))
                .map(rac -> ((JSONObject) rac).get(ROLES_CLAIM))
                .map(rc -> (Collection<String>) rc).orElse(emptyList());

        return Stream.concat(realmAccessRoles.stream(), resourceAccessRoles.stream())
                .distinct().collect(toList());
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}