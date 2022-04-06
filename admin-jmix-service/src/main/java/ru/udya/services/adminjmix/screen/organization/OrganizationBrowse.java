package ru.udya.services.adminjmix.screen.organization;

import io.jmix.core.LoadContext;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.gateway.api.client.OrganizationControllerApi;
import ru.udya.services.adminjmix.entity.Organization;
import ru.udya.services.adminjmix.mapper.OrganizationMapper;

import java.util.List;

@UiController("adm_Organization.browse")
@UiDescriptor("organization-browse.xml")
@LookupComponent("organizationsTable")
public class OrganizationBrowse extends StandardLookup<Organization> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationBrowse.class);

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private OrganizationControllerApi organizationControllerApi;

    @Install(to = "organizationsDl", target = Target.DATA_LOADER)
    private List<Organization> organizationsDlLoadDelegate(LoadContext<Organization> loadContext) {
        return organizationControllerApi.findAllOrganizations()
                .map(organizationMapper::organizationDtoToOrganization)
                .collectList()
                .doOnError(throwable -> {
                    LOGGER.error("Something went wrong", throwable);
                })
                .block();
    }
}