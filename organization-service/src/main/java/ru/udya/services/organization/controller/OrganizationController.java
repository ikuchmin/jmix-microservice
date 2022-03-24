package ru.udya.services.organization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.udya.services.employee.api.client.EmployeeApiClient;
import ru.udya.services.organization.client.DepartmentClient;
import ru.udya.services.organization.controller.mapper.EmployeeMapper;
import ru.udya.services.organization.model.Employee;
import ru.udya.services.organization.model.Organization;
import ru.udya.services.organization.repository.OrganizationRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrganizationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	OrganizationRepository repository;

	@Autowired
	DepartmentClient departmentClient;

	@Autowired
	EmployeeApiClient employeeClient;
	
	@PostMapping
	public Organization add(@RequestBody Organization organization) {
		LOGGER.info("Organization add: {}", organization);
		return repository.add(organization);
	}
	
	@GetMapping
	public List<Organization> findAll() {
		LOGGER.info("Organization find");
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Organization findById(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		return repository.findById(id);
	}

	@GetMapping("/{id}/with-departments")
	public Organization findByIdWithDepartments(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Organization organization = repository.findById(id);
		organization.setDepartments(departmentClient.findByOrganization(organization.getId()));
		return organization;
	}
	
	@GetMapping("/{id}/with-departments-and-employees")
	public Organization findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Organization organization = repository.findById(id);
		organization.setDepartments(departmentClient.findByOrganizationWithEmployees(organization.getId()));
		return organization;
	}
	
	@GetMapping("/{id}/with-employees")
	public Organization findByIdWithEmployees(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Organization organization = repository.findById(id);

		var foundEmployeesResponse = employeeClient.findByOrganization(organization.getId());

		//noinspection ConstantConditions
		List<Employee> foundEmployees = foundEmployeesResponse.getBody().stream()
					.map(EmployeeMapper.INSTANCE::employeeDtoToEmployee)
					.collect(Collectors.toList());

		organization.setEmployees(foundEmployees);

		return organization;
	}
	
}
