package ru.udya.services.department.repository;


import org.apache.commons.lang3.SerializationUtils;
import ru.udya.services.department.model.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DepartmentRepository {

    private final List<Department> departments = new ArrayList<>();

    public Department add(Department department) {
        department.setId((long) (departments.size() + 1));
        departments.add(department);
        return department;
    }

    public Department findById(Long id) {
        Optional<Department> department = departments.stream()
                .filter(a -> a.getId().equals(id)).findFirst();

        return department.map(value -> (Department) SerializationUtils.clone(value)).orElse(null);
    }

    public List<Department> findAll() {
        return departments.stream()
                .map(d -> (Department) SerializationUtils.clone(d))
                .collect(Collectors.toList());
    }

    public List<Department> findByOrganization(Long organizationId) {
        return departments.stream()
                .filter(a -> a.getOrganizationId().equals(organizationId))
                .map(d -> (Department) SerializationUtils.clone(d))
                .collect(Collectors.toList());
    }

}
