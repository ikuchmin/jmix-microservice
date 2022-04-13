package ru.udya.services.adminjmix.screen.organization;

import io.jmix.core.SaveContext;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.adminjmix.adapter.OrganizationAdapter;
import ru.udya.services.adminjmix.entity.Organization;

import java.util.Set;

@UiController("adm_Organization.edit")
@UiDescriptor("organization-edit.xml")
@EditedEntityContainer("organizationDc")
public class OrganizationEdit extends StandardEditor<Organization> {
    @Autowired
    private OrganizationAdapter organizationAdapter;

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> commitDelegate(SaveContext saveContext) {
        organizationAdapter.create(getEditedEntity());
        return null;
    }
}