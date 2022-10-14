package ru.udya.services.employee.repository;

import org.apache.commons.lang3.SerializationUtils;
import ru.udya.services.employee.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public Employee add(Employee employee) {
        employee.setId((long) (employees.size() + 1));
        employees.add(employee);
        return employee;
    }

    public Employee findById(Long id) {
        Optional<Employee> employee = employees.stream()
                .filter(a -> a.getId().equals(id)).findFirst();

        return employee.map(value -> (Employee) SerializationUtils.clone(value)).orElse(null);
    }

    public List<Employee> findAll() {
        return employees.stream()
                .map(d -> (Employee) SerializationUtils.clone(d))
                .collect(Collectors.toList());
    }

    public List<Employee> findByDepartment(Long departmentId) {
        return employees.stream()
                .filter(a -> a.getDepartmentId().equals(departmentId))
                .map(d -> (Employee) SerializationUtils.clone(d))
                .collect(Collectors.toList());
    }

    public List<Employee> findByOrganization(Long organizationId) {
        return employees.stream()
                .filter(a -> a.getOrganizationId().equals(organizationId))
                .map(d -> (Employee) SerializationUtils.clone(d))
                .collect(Collectors.toList());
    }

}
