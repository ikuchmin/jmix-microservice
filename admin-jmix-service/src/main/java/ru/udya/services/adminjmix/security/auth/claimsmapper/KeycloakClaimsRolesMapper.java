package ru.udya.services.adminjmix.security.auth.claimsmapper;

import com.nimbusds.jose.shaded.json.JSONObject;
import io.jmix.oidc.claimsmapper.BaseClaimsRolesMapper;
import io.jmix.security.role.ResourceRoleRepository;
import io.jmix.security.role.RowLevelRoleRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class KeycloakClaimsRolesMapper extends BaseClaimsRolesMapper {

    private static final String REALM_ACCESS_CLAIM = "realm_access";
    private static final String RESOURCE_ACCESS_CLAIM = "resource_access";
    private static final String ROLES_CLAIM = "roles";

    protected String resourceId = "";

    public KeycloakClaimsRolesMapper(ResourceRoleRepository resourceRoleRepository,
                                     RowLevelRoleRepository rowLevelRoleRepository) {
        super(resourceRoleRepository, rowLevelRoleRepository);
    }

    @Override
    protected Collection<String> getResourceRolesCodes(Map<String, Object> claims) {
        return getRolesCodes(claims);
    }

    @Override
    protected Collection<String> getRowLevelRoleCodes(Map<String, Object> claims) {
        return getRolesCodes(claims);
    }

    protected Collection<String> getRolesCodes(Map<String, Object> claims) {
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
