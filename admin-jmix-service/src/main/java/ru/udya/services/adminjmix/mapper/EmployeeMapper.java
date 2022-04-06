package ru.udya.services.adminjmix.mapper;

import io.jmix.core.Metadata;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.gateway.api.model.EmployeeDto;
import ru.udya.services.adminjmix.entity.Employee;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {
    @Autowired
    protected Metadata metadata;

    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = metadata.create(Employee.class);
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setAge(employeeDto.getAge());
        employee.setPosition(employeeDto.getPosition());
        employee.setDepartmentId(employeeDto.getDepartmentId());
        employee.setOrganizationId(employeeDto.getOrganizationId());
        return employee;
    }

    public abstract EmployeeDto employeeToEmployeeDto(Employee department);
}
