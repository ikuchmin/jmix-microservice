package ru.udya.services.adminjmix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.udya.services.adminjmix.client.adapter.DepartmentAdapter;
import ru.udya.services.adminjmix.client.adapter.OrganizationAdapter;
import ru.udya.services.adminjmix.entity.Employee;
import ru.udya.services.gateway.api.model.EmployeeDto;

@Mapper(componentModel = "spring", uses = {DepartmentAdapter.class, OrganizationAdapter.class})
public abstract class EmployeeMapper {

    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "organizationId", target = "organization")
    public abstract Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "organization.id", target = "organizationId")
    public abstract EmployeeDto employeeToEmployeeDto(Employee employee);

}
