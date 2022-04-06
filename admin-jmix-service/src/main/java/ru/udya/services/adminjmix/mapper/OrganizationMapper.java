package ru.udya.services.adminjmix.mapper;

import io.jmix.core.Metadata;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.gateway.api.model.OrganizationDto;
import ru.udya.services.adminjmix.entity.Organization;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class OrganizationMapper {
    @Autowired
    protected Metadata metadata;

    public Organization organizationDtoToOrganization(OrganizationDto organizationDto) {
        Organization organization = metadata.create(Organization.class);
        organization.setUuid(UUID.randomUUID());
        organization.setId(organizationDto.getId());
        organization.setName(organizationDto.getName());
        organization.setAddress(organizationDto.getAddress());
        return organization;
    }

    public abstract OrganizationDto organizationToOrganizationDto(Organization department);
}
