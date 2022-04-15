package ru.udya.services.adminjmix.client.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.udya.services.adminjmix.entity.Department;
import ru.udya.services.adminjmix.mapper.DepartmentMapper;
import ru.udya.services.gateway.api.client.DepartmentControllerApi;
import ru.udya.services.gateway.api.model.DepartmentDto;

import java.util.List;
import java.util.stream.Collectors;

@Component("adm_DepartmentAdapter")
public class DepartmentAdapter {
    @Autowired
    private DepartmentControllerApi departmentControllerApi;

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> findAll() {
        return departmentControllerApi.findAllDepartments()
                .toStream()
                .map(departmentMapper::departmentDtoToDepartment)
                .collect(Collectors.toList());
    }

    public Department findById(Long id) {
        DepartmentDto dto = departmentControllerApi.findDepartmentById(id).block();
        return departmentMapper.departmentDtoToDepartment(dto);
    }

    public Department create(Department department) {
        var departmentDto = departmentMapper.departmentToDepartmentDto(department);

        DepartmentDto dto = departmentControllerApi
                .addDepartment(departmentDto).block();

        return departmentMapper.departmentDtoToDepartment(dto);
    }

    public List<Department> findDepartments(Long organizationId) {
        return departmentControllerApi.findDepartmentByOrganization(organizationId)
                .toStream()
                .map(departmentMapper::departmentDtoToDepartment)
                .collect(Collectors.toList());
    }
}