package ru.udya.services.adminjmix.screen.department;

import io.jmix.core.LoadContext;
import io.jmix.core.SaveContext;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.adminjmix.client.adapter.DepartmentAdapter;
import ru.udya.services.adminjmix.client.adapter.EmployeeAdapter;
import ru.udya.services.adminjmix.client.adapter.OrganizationAdapter;
import ru.udya.services.adminjmix.entity.Department;
import ru.udya.services.adminjmix.entity.Organization;

import java.util.List;
import java.util.Set;

@UiController("adm_Department.edit")
@UiDescriptor("department-edit.xml")
@EditedEntityContainer("departmentDc")
public class DepartmentEdit extends StandardEditor<Department> {

    @Autowired
    private DepartmentAdapter departmentAdapter;

    @Autowired
    private OrganizationAdapter organizationAdapter;

    @Autowired
    private EmployeeAdapter employeeAdapter;

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> commitDelegate(SaveContext saveContext) {
        Department department = departmentAdapter.create(getEditedEntity());
        return Set.of(department);
    }

    @Install(to = "organizationsDl", target = Target.DATA_LOADER)
    private List<Organization> organizationsDlLoadDelegate(LoadContext<Organization> loadContext) {
        return organizationAdapter.findAll();
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        Department department = getEditedEntity();
        Long departmentId = department.getId();
        if (departmentId != null) {
            department.setEmployees(employeeAdapter.findEmployeeByDepartmentId(departmentId));
        }
    }
}