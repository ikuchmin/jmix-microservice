package ru.udya.services.adminjmix.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.udya.services.adminjmix.entity.Employee;
import ru.udya.services.adminjmix.mapper.EmployeeMapper;
import ru.udya.services.gateway.api.client.EmployeeControllerApi;
import ru.udya.services.gateway.api.model.EmployeeDto;

import java.util.List;

@Component("adm_EmployeeAdapter")
public class EmployeeAdapter {
    @Autowired
    private EmployeeControllerApi employeeControllerApi;

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> findAll() {
        return employeeControllerApi.findAllEmployees()
                .map(employeeMapper::employeeDtoToEmployee)
                .collectList()
                .block();
    }

    public Employee save(Employee employee) {
        EmployeeDto dto = employeeControllerApi.addEmployee(employeeMapper.employeeToEmployeeDto(employee)).block();
        return employeeMapper.employeeDtoToEmployee(dto);
    }
}