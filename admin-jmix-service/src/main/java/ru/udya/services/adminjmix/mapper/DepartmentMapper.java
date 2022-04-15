package ru.udya.services.adminjmix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.udya.services.adminjmix.client.adapter.OrganizationAdapter;
import ru.udya.services.adminjmix.entity.Department;
import ru.udya.services.gateway.api.model.DepartmentDto;

@Mapper(componentModel = "spring", uses = {OrganizationAdapter.class})
public abstract class DepartmentMapper {

    @Mapping(source = "organizationId", target = "organization")
    public abstract Department departmentDtoToDepartment(DepartmentDto departmentDto);

    @Mapping(source = "organization.id", target = "organizationId")
    public abstract DepartmentDto departmentToDepartmentDto(Department department);
}
