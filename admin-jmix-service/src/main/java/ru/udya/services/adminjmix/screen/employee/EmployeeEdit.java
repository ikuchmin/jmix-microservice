package ru.udya.services.adminjmix.screen.employee;

import io.jmix.core.LoadContext;
import io.jmix.core.SaveContext;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.adminjmix.client.adapter.DepartmentAdapter;
import ru.udya.services.adminjmix.client.adapter.EmployeeAdapter;
import ru.udya.services.adminjmix.client.adapter.OrganizationAdapter;
import ru.udya.services.adminjmix.entity.Department;
import ru.udya.services.adminjmix.entity.Employee;
import ru.udya.services.adminjmix.entity.Organization;

import java.util.List;
import java.util.Set;

@UiController("adm_Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
public class EmployeeEdit extends StandardEditor<Employee> {

    @Autowired
    private EmployeeAdapter employeeAdapter;

    @Autowired
    private DepartmentAdapter departmentAdapter;

    @Autowired
    private OrganizationAdapter organizationAdapter;

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> commitDelegate(SaveContext saveContext) {
        Employee employee = employeeAdapter.create(getEditedEntity());
        return Set.of(employee);
    }

    @Install(to = "departmentsDl", target = Target.DATA_LOADER)
    private List<Department> departmentsDlLoadDelegate(LoadContext<Department> loadContext) {
        return departmentAdapter.findAll();
    }

    @Install(to = "organizationsDl", target = Target.DATA_LOADER)
    private List<Organization> organizationsDlLoadDelegate(LoadContext<Organization> loadContext) {
        return organizationAdapter.findAll();
    }
}