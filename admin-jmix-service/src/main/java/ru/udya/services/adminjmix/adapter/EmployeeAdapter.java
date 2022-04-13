package ru.udya.services.adminjmix.adapter;

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

    public Employee create(Employee employee) {
        var employeeDto = employeeMapper.employeeToEmployeeDto(employee);

        EmployeeDto dto = employeeControllerApi
                .addEmployee(employeeDto).block();

        return employeeMapper.employeeDtoToEmployee(dto);
    }
}