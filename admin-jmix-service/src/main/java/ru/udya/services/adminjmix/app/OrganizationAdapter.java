package ru.udya.services.adminjmix.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.udya.services.adminjmix.entity.Organization;
import ru.udya.services.adminjmix.mapper.OrganizationMapper;
import ru.udya.services.gateway.api.client.OrganizationControllerApi;
import ru.udya.services.gateway.api.model.OrganizationDto;

import java.util.List;

@Component("adm_OrganizationAdapter")
public class OrganizationAdapter {
    @Autowired
    private OrganizationControllerApi organizationControllerApi;

    @Autowired
    private OrganizationMapper organizationMapper;

    public List<Organization> findAll() {
        return organizationControllerApi.findAllOrganizations()
                .map(organizationMapper::organizationDtoToOrganization)
                .collectList()
                .block();
    }

    public Organization save(Organization organization) {
        OrganizationDto organizationDto = organizationMapper.organizationToOrganizationDto(organization);
        OrganizationDto dto = organizationControllerApi.addOrganization(organizationDto).block();
        return organizationMapper.organizationDtoToOrganization(dto);
    }
}