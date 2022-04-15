package ru.udya.services.adminjmix.screen.employee;

import io.jmix.core.LoadContext;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.adminjmix.client.adapter.EmployeeAdapter;
import ru.udya.services.adminjmix.entity.Employee;

import java.util.List;

@UiController("adm_Employee.browse")
@UiDescriptor("employee-browse.xml")
@LookupComponent("employeesTable")
public class EmployeeBrowse extends StandardLookup<Employee> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeBrowse.class);

    @Autowired
    private EmployeeAdapter employeeAdapter;
    @Autowired
    private CollectionLoader<Employee> employeesDl;

    @Install(to = "employeesDl", target = Target.DATA_LOADER)
    private List<Employee> employeesDlLoadDelegate(LoadContext<Employee> loadContext) {
        return employeeAdapter.findAll();
    }

    @Install(to = "employeesTable.create", subject = "afterCommitHandler")
    private void employeesTableCreateAfterCommitHandler(Employee employee) {
        employeesDl.load();
    }
}