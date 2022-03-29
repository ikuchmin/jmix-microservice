package ru.udya.services.organization.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.udya.services.employee.api.model.EmployeeDto;
import ru.udya.services.organization.api.model.OrganizationDto;
import ru.udya.services.organization.model.Employee;
import ru.udya.services.organization.model.Organization;

@Mapper
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper( OrganizationMapper.class );

    Organization organizationDtoToOrganization(OrganizationDto organizationDto);

    OrganizationDto organizationToOrganizationDto(Organization organization);
}
