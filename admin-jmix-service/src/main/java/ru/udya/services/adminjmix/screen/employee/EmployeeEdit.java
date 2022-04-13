package ru.udya.services.adminjmix.screen.employee;

import io.jmix.core.SaveContext;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.adminjmix.client.adapter.EmployeeAdapter;
import ru.udya.services.adminjmix.entity.Employee;

import java.util.Set;

@UiController("adm_Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
public class EmployeeEdit extends StandardEditor<Employee> {
    @Autowired
    private EmployeeAdapter employeeAdapter;

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> commitDelegate(SaveContext saveContext) {
        Employee employee = employeeAdapter.create(getEditedEntity());
        return Set.of(employee);
    }
}