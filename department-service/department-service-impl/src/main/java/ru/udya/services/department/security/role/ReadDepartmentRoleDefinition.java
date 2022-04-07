package ru.udya.services.department.security.role;

import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResourceRole(
        name = "Allow to read Departments",
        code = "read-department")
public interface ReadDepartmentRoleDefinition {

    @SpecificPolicy(resources = {"read-department"})
    void department();

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("hasAnyAuthority('read-department')")
    @interface ReadDepartmentRole {

    }
}
