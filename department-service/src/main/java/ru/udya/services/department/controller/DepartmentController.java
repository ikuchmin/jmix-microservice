package ru.udya.services.department.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.udya.services.department.controller.mapper.EmployeeMapper;
import ru.udya.services.department.model.Department;
import ru.udya.services.department.repository.DepartmentRepository;
import ru.udya.services.employee.api.client.EmployeeApi;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/public")
public class DepartmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	DepartmentRepository repository;

	@Autowired
	EmployeeApi employeeClient;
	
	@PostMapping("/")
	public Department add(@RequestBody Department department) {
		LOGGER.info("Department add: {}", department);
		return repository.add(department);
	}
	
	@GetMapping("/{id}")
	public Department findById(@PathVariable("id") Long id) {
		LOGGER.info("Department find: id={}", id);
		return repository.findById(id);
	}
	
	@GetMapping("/")
	public List<Department> findAll() {
		LOGGER.info("Department find");
		return repository.findAll();
	}
	
	@GetMapping("/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		return repository.findByOrganization(organizationId);
	}
	
	@GetMapping("/organization/{organizationId}/with-employees")
	public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		List<Department> departments = repository.findByOrganization(organizationId);


		for (Department d : departments) {
			var foundEmployeesResponse  = employeeClient.findByDepartment(d.getId());

			var foundEmployees = foundEmployeesResponse
					.map(EmployeeMapper.INSTANCE::employeeDtoToEmployee)
					.collect(Collectors.toList()).block();


//			//noinspection ConstantConditions
//			var foundEmployees = foundEmployeesResponse.getBody().stream()
//					.map(EmployeeMapper.INSTANCE::employeeDtoToEmployee)
//					.collect(Collectors.toList());

			d.setEmployees(foundEmployees);
		}

		return departments;
	}
	
}
