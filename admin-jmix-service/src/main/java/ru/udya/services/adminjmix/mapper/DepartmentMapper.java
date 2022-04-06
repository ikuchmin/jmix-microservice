package ru.udya.services.adminjmix.mapper;

import io.jmix.core.Metadata;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.udya.services.gateway.api.model.DepartmentDto;
import ru.udya.services.adminjmix.entity.Department;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class DepartmentMapper {
    @Autowired
    protected Metadata metadata;

    public Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        Department department = metadata.create(Department.class);
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        return department;
    }

    public abstract DepartmentDto departmentToDepartmentDto(Department department);
}
