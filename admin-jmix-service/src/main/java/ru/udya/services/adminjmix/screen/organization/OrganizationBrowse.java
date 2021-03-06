package ru.udya.services.adminjmix.screen.organization;

import io.jmix.core.LoadContext;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.adminjmix.client.adapter.OrganizationAdapter;
import ru.udya.services.adminjmix.entity.Employee;
import ru.udya.services.adminjmix.entity.Organization;

import java.util.List;

@UiController("adm_Organization.browse")
@UiDescriptor("organization-browse.xml")
@LookupComponent("organizationsTable")
public class OrganizationBrowse extends StandardLookup<Organization> {
    @Autowired
    private OrganizationAdapter organizationAdapter;
    @Autowired
    private CollectionLoader<Organization> organizationsDl;

    @Install(to = "organizationsDl", target = Target.DATA_LOADER)
    private List<Organization> organizationsDlLoadDelegate(LoadContext<Organization> loadContext) {
        return organizationAdapter.findAll();
    }

    @Install(to = "organizationsTable.create", subject = "afterCommitHandler")
    private void organizationsTableCreateAfterCommitHandler(Organization organization) {
        organizationsDl.load();
    }
}