package ru.udya.services.department.repository;


import ru.udya.services.department.model.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DepartmentRepository {

	private final List<Department> departments = new ArrayList<>();
	
	public Department add(Department department) {
		department.setId((long) (departments.size()+1));
		departments.add(department);
		return department;
	}
	
	public Department findById(Long id) {
		Optional<Department> department = departments.stream().filter(a -> a.getId().equals(id)).findFirst();
		if (department.isPresent())
			return department.get();
		else
			return null;
	}
	
	public List<Department> findAll() {
		return departments;
	}
	
	public List<Department> findByOrganization(Long organizationId) {
		return departments.stream().filter(a -> a.getOrganizationId().equals(organizationId)).collect(Collectors.toList());
	}
	
}
