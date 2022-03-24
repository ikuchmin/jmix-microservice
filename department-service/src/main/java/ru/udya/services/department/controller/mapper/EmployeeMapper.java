package ru.udya.services.department.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.udya.services.department.model.Employee;
import ru.udya.services.employee.api.model.EmployeeDto;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class );

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

}
