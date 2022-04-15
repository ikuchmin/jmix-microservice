package ru.udya.services.adminjmix.client.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.udya.services.adminjmix.entity.Organization;
import ru.udya.services.adminjmix.mapper.OrganizationMapper;
import ru.udya.services.gateway.api.client.OrganizationControllerApi;
import ru.udya.services.gateway.api.model.OrganizationDto;

import java.util.List;
import java.util.stream.Collectors;

@Component("adm_OrganizationAdapter")
public class OrganizationAdapter {
    @Autowired
    private OrganizationControllerApi organizationControllerApi;

    @Autowired
    private OrganizationMapper organizationMapper;

    public List<Organization> findAll() {
        return organizationControllerApi.findAllOrganizations()
                .toStream()
                .map(organizationMapper::organizationDtoToOrganization)
                .collect(Collectors.toList());
    }

    public Organization findById(Long id) {
        OrganizationDto dto = organizationControllerApi.findOrganizationById(id).block();
        return organizationMapper.organizationDtoToOrganization(dto);
    }

    public Organization create(Organization organization) {
        OrganizationDto organizationDto = organizationMapper
                .organizationToOrganizationDto(organization);

        OrganizationDto dto = organizationControllerApi
                .addOrganization(organizationDto).block();

        return organizationMapper.organizationDtoToOrganization(dto);
    }
}