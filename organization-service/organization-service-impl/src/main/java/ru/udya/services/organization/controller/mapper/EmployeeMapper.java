package ru.udya.services.organization.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.udya.services.employee.api.model.EmployeeDto;
import ru.udya.services.organization.model.Employee;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class );

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

}
