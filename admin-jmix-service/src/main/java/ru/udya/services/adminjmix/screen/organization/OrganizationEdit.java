package ru.udya.services.adminjmix.screen.organization;

import io.jmix.core.SaveContext;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.adminjmix.client.adapter.DepartmentAdapter;
import ru.udya.services.adminjmix.client.adapter.EmployeeAdapter;
import ru.udya.services.adminjmix.client.adapter.OrganizationAdapter;
import ru.udya.services.adminjmix.entity.Organization;

import java.util.Set;

@UiController("adm_Organization.edit")
@UiDescriptor("organization-edit.xml")
@EditedEntityContainer("organizationDc")
public class OrganizationEdit extends StandardEditor<Organization> {
    @Autowired
    private OrganizationAdapter organizationAdapter;
    @Autowired
    private DepartmentAdapter departmentAdapter;
    @Autowired
    private EmployeeAdapter employeeAdapter;

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> commitDelegate(SaveContext saveContext) {
        Organization organization = organizationAdapter.create(getEditedEntity());
        return Set.of(organization);
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        Organization organization = getEditedEntity();
        Long organizationId = organization.getId();
        if (organizationId != null) {
            organization.setDepartments(departmentAdapter.findDepartments(organizationId));
            organization.setEmployees(employeeAdapter.findEmployeeByOrganizationId(organizationId));
        }
    }
}