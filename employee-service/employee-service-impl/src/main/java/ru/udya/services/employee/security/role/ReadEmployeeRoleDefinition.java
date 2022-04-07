package ru.udya.services.employee.security.role;

import io.jmix.security.role.annotation.ResourceRole;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResourceRole(
        name = "Allow to read Employee",
        code = ReadEmployeeRoleDefinition.CODE)
public interface ReadEmployeeRoleDefinition {

    String CODE = "read-employee";

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("hasAnyAuthority('" + CODE + "')")
    @interface ReadEmployeeRole {

    }
}
