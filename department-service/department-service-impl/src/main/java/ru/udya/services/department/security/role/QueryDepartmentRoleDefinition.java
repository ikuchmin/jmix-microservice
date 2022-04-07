package ru.udya.services.department.security.role;

import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ResourceRole(
        name = "Allow to query Departments",
        code = "query-department")
public interface QueryDepartmentRoleDefinition {

    @SpecificPolicy(resources = {"query-department"})
    void department();

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("hasAnyAuthority('query-department')")
    @interface QueryDepartmentRole {

    }
}
