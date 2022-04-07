package ru.udya.services.employee.security.role;

import io.jmix.security.role.annotation.ResourceRole;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResourceRole(
        name = "Allow to manage Employee",
        code = ManageEmployeeRoleDefinition.CODE)
public interface ManageEmployeeRoleDefinition {

    String CODE = "manage-employee";

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("hasAnyAuthority('" + CODE + "')")
    @interface ManageEmployeeRole {

    }
}
