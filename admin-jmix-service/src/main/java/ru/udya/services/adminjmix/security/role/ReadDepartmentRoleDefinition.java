package ru.udya.services.adminjmix.security.role;

import io.jmix.security.role.annotation.ResourceRole;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResourceRole(
        name = "Allow to read Departments",
        code = ReadDepartmentRoleDefinition.CODE)
public interface ReadDepartmentRoleDefinition {

    String CODE = "read-department";

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("hasAnyAuthority('" + CODE + "')")
    @interface ReadDepartmentRole {

    }
}
