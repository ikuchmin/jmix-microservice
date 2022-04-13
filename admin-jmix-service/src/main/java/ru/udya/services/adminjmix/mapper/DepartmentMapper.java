package ru.udya.services.adminjmix.mapper;

import org.mapstruct.Mapper;
import ru.udya.services.adminjmix.entity.Department;
import ru.udya.services.gateway.api.model.DepartmentDto;

@Mapper(componentModel = "spring")
public abstract class DepartmentMapper {
    public abstract Department departmentDtoToDepartment(DepartmentDto departmentDto);
    public abstract DepartmentDto departmentToDepartmentDto(Department department);
}
