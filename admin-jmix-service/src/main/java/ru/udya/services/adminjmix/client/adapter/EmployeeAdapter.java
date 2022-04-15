package ru.udya.services.adminjmix.client.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.udya.services.adminjmix.entity.Employee;
import ru.udya.services.adminjmix.mapper.EmployeeMapper;
import ru.udya.services.gateway.api.client.EmployeeControllerApi;
import ru.udya.services.gateway.api.model.EmployeeDto;

import java.util.List;
import java.util.stream.Collectors;

@Component("adm_EmployeeAdapter")
public class EmployeeAdapter {
    @Autowired
    private EmployeeControllerApi employeeControllerApi;

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> findAll() {
        return employeeControllerApi.findAllEmployees()
                .toStream()
                .map(employeeMapper::employeeDtoToEmployee)
                .collect(Collectors.toList());
    }

    public Employee findById(Long id) {
        EmployeeDto dto = employeeControllerApi.findEmployeeById(id).block();
        return employeeMapper.employeeDtoToEmployee(dto);
    }

    public Employee create(Employee employee) {
        var employeeDto = employeeMapper.employeeToEmployeeDto(employee);

        EmployeeDto dto = employeeControllerApi.addEmployee(employeeDto).block();

        return employeeMapper.employeeDtoToEmployee(dto);
    }

    public List<Employee> findEmployeeByOrganizationId(Long organizationId) {
        return employeeControllerApi.findEmployeeByOrganization(organizationId)
                .toStream()
                .map(employeeMapper::employeeDtoToEmployee)
                .collect(Collectors.toList());
    }

    public List<Employee> findEmployeeByDepartmentId(Long departmentId) {
        return employeeControllerApi.findEmployeeByDepartment(departmentId)
                .toStream()
                .map(employeeMapper::employeeDtoToEmployee)
                .collect(Collectors.toList());
    }
}