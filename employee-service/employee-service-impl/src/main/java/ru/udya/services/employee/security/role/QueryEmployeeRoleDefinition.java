package ru.udya.services.employee.security.role;

import io.jmix.security.role.annotation.ResourceRole;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResourceRole(
        name = "Allow to query Employee",
        code = QueryEmployeeRoleDefinition.CODE)
public interface QueryEmployeeRoleDefinition {

    String CODE = "query-employee";

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("hasAnyAuthority('" + CODE + "')")
    @interface QueryEmployeeRole {

    }
}
