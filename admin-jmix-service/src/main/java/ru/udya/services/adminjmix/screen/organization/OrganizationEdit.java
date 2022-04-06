package ru.udya.services.adminjmix.screen.organization;

import io.jmix.ui.screen.*;
import ru.udya.services.adminjmix.entity.Organization;

@UiController("adm_Organization.edit")
@UiDescriptor("organization-edit.xml")
@EditedEntityContainer("organizationDc")
public class OrganizationEdit extends StandardEditor<Organization> {
}