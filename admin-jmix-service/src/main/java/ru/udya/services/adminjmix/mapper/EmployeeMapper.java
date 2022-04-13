package ru.udya.services.adminjmix.mapper;

import org.mapstruct.Mapper;
import ru.udya.services.adminjmix.entity.Employee;
import ru.udya.services.gateway.api.model.EmployeeDto;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {
    public abstract Employee employeeDtoToEmployee(EmployeeDto employeeDto);
    public abstract EmployeeDto employeeToEmployeeDto(Employee employee);
}
