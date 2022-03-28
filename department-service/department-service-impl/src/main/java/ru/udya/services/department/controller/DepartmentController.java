package ru.udya.services.department.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.udya.services.department.api.model.DepartmentDto;
import ru.udya.services.department.controller.mapper.DepartmentMapper;
import ru.udya.services.department.controller.mapper.EmployeeMapper;
import ru.udya.services.department.model.Department;
import ru.udya.services.department.repository.DepartmentRepository;
import ru.udya.services.employee.api.client.EmployeeApi;
import ru.udya.services.department.api.controller.DepartmentApi;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/public")
public class DepartmentController implements DepartmentApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	DepartmentRepository repository;

	@Autowired
	EmployeeApi employeeClient;

	@Override
	public ResponseEntity<DepartmentDto> create(DepartmentDto departmentDto) {
		LOGGER.info("Department add: {}", departmentDto);

		var department = DepartmentMapper.INSTANCE.departmentDtoToDepartment(departmentDto);

		var savedDepartment = repository.add(department);

		var savedDepartmentDto = DepartmentMapper.INSTANCE.departmentToDepartmentDto(savedDepartment);

		return ResponseEntity.ok(savedDepartmentDto);
	}

	@Override
	public ResponseEntity<List<DepartmentDto>> findAll() {
		LOGGER.info("Department find");
		var foundDepartments = repository.findAll();

		var foundDepartmentDtos = foundDepartments.stream()
				.map(DepartmentMapper.INSTANCE::departmentToDepartmentDto)
				.collect(Collectors.toList());

		return ResponseEntity.ok(foundDepartmentDtos);
	}

	@Override
	public ResponseEntity<DepartmentDto> findById(Long id) {
		LOGGER.info("Department find: id={}", id);


		var foundDepartment = repository.findById(id);

		DepartmentDto foundDepartmentDto = new DepartmentDto();
		if (foundDepartment != null) {
			foundDepartmentDto = DepartmentMapper.INSTANCE
					.departmentToDepartmentDto(foundDepartment);
		}

		return ResponseEntity.ok(foundDepartmentDto);
	}

	@Override
	public ResponseEntity<List<DepartmentDto>> findByOrganization(Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		var foundDepartments = repository.findByOrganization(organizationId);

		var foundDepartmentDtos = foundDepartments.stream()
				.map(DepartmentMapper.INSTANCE::departmentToDepartmentDto)
				.collect(Collectors.toList());

		return ResponseEntity.ok(foundDepartmentDtos);
	}

	@Override
	public ResponseEntity<List<DepartmentDto>> findByOrganizationWithEmployees(Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		List<Department> foundDepartments = repository.findByOrganization(organizationId);

		LOGGER.info("Found departments: departments={}", foundDepartments);

		for (Department d : foundDepartments) {
			var foundEmployeesResponse  = employeeClient.findByDepartment(d.getId());

			var foundEmployees = foundEmployeesResponse
					.map(EmployeeMapper.INSTANCE::employeeDtoToEmployee)
					.collect(Collectors.toList()).block();

			d.setEmployees(foundEmployees);
		}

		var foundDepartmentDtos = foundDepartments.stream()
				.map(DepartmentMapper.INSTANCE::departmentToDepartmentDto)
				.collect(Collectors.toList());

		return ResponseEntity.ok(foundDepartmentDtos);
	}
}
