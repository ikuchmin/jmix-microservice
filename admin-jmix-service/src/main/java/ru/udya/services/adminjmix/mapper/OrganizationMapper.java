package ru.udya.services.adminjmix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.udya.services.adminjmix.client.adapter.DepartmentAdapter;
import ru.udya.services.adminjmix.client.adapter.EmployeeAdapter;
import ru.udya.services.adminjmix.entity.Organization;
import ru.udya.services.gateway.api.model.OrganizationDto;

@Mapper(componentModel = "spring")
public abstract class OrganizationMapper {

    public abstract Organization organizationDtoToOrganization(OrganizationDto organizationDto);

    public abstract OrganizationDto organizationToOrganizationDto(Organization department);
}
