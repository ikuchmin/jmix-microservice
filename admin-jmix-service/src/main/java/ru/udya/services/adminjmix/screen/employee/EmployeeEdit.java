package ru.udya.services.adminjmix.screen.employee;

import io.jmix.ui.screen.*;
import ru.udya.services.adminjmix.entity.Employee;

@UiController("adm_Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
public class EmployeeEdit extends StandardEditor<Employee> {
}