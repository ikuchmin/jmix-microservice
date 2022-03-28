package ru.udya.services.department.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.udya.services.department.model.Department;
import ru.udya.services.department.model.Employee;
import ru.udya.services.department.api.model.DepartmentDto;
import ru.udya.services.department.api.model.EmployeeDto;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper( DepartmentMapper.class );

    Department departmentDtoToDepartment(DepartmentDto employeeDto);

    DepartmentDto departmentToDepartmentDto(Department department);
}
