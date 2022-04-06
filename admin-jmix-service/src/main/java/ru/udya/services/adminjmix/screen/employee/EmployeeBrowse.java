package ru.udya.services.adminjmix.screen.employee;

import io.jmix.core.LoadContext;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.gateway.api.client.EmployeeControllerApi;
import ru.udya.services.adminjmix.entity.Employee;
import ru.udya.services.adminjmix.mapper.EmployeeMapper;

import java.time.Duration;
import java.util.List;

@UiController("adm_Employee.browse")
@UiDescriptor("employee-browse.xml")
@LookupComponent("employeesTable")
public class EmployeeBrowse extends StandardLookup<Employee> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeBrowse.class);

    @Autowired
    private EmployeeControllerApi employeeControllerApi;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Install(to = "employeesDl", target = Target.DATA_LOADER)
    private List<Employee> employeesDlLoadDelegate(LoadContext<Employee> loadContext) {
        return employeeControllerApi.findAllEmployees()
                .map(employeeMapper::employeeDtoToEmployee)
                .collectList()
                .doOnError(throwable -> {
                    LOGGER.error("Something went wrong", throwable);
                })
                .block(Duration.ofSeconds(1));
    }
}