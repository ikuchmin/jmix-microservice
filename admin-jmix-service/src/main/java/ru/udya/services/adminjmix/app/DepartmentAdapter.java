package ru.udya.services.adminjmix.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.udya.services.adminjmix.entity.Department;
import ru.udya.services.adminjmix.mapper.DepartmentMapper;
import ru.udya.services.gateway.api.client.DepartmentControllerApi;
import ru.udya.services.gateway.api.model.DepartmentDto;

import java.util.List;

@Component("adm_DepartmentAdapter")
public class DepartmentAdapter {
    @Autowired
    private DepartmentControllerApi departmentControllerApi;

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> findAll() {
        return departmentControllerApi.findAllDepartments()
                .map(departmentMapper::departmentDtoToDepartment)
                .collectList()
                .block();
    }

    public Department save(Department department) {
        DepartmentDto dto = departmentControllerApi.addDepartment(departmentMapper.departmentToDepartmentDto(department)).block();
        return departmentMapper.departmentDtoToDepartment(dto);
    }
}