package ru.udya.services.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.udya.services.employee.api.controller.EmployeeApi;
import ru.udya.services.employee.api.model.EmployeeDto;
import ru.udya.services.employee.controller.mapper.EmployeeMapper;
import ru.udya.services.employee.repository.EmployeeRepository;
import ru.udya.services.employee.security.role.ManageEmployeeRoleDefinition;
import ru.udya.services.employee.security.role.ManageEmployeeRoleDefinition.ManageEmployeeRole;
import ru.udya.services.employee.security.role.QueryEmployeeRoleDefinition;
import ru.udya.services.employee.security.role.QueryEmployeeRoleDefinition.QueryEmployeeRole;
import ru.udya.services.employee.security.role.ReadEmployeeRoleDefinition;
import ru.udya.services.employee.security.role.ReadEmployeeRoleDefinition.ReadEmployeeRole;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController implements EmployeeApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeRepository repository;

	@Override
	@ManageEmployeeRole
	public ResponseEntity<EmployeeDto> create(EmployeeDto employeeDto) {
		LOGGER.info("Employee add: {}", employeeDto);

		var employee = EmployeeMapper.INSTANCE.employeeDtoToEmployee(employeeDto);

		var savedEmployee = repository.add(employee);

		var savedEmployeeDto = EmployeeMapper.INSTANCE.employeeToEmployeeDto(savedEmployee);

		return ResponseEntity.ok(savedEmployeeDto);
	}

	@Override
	@ReadEmployeeRole
	public ResponseEntity<List<EmployeeDto>> findAll() {
		LOGGER.info("Employee find");
		var foundEmployees = repository.findAll();

		var foundEmployeeDtos = foundEmployees.stream()
				.map(EmployeeMapper.INSTANCE::employeeToEmployeeDto)
				.collect(Collectors.toList());

		return ResponseEntity.ok(foundEmployeeDtos);
	}

	@Override
	@QueryEmployeeRole
	public ResponseEntity<List<EmployeeDto>> findByDepartment(Long departmentId) {
		LOGGER.info("Employee find: departmentId={}", departmentId);

		var foundEmployees = repository.findByDepartment(departmentId);

		var foundEmployeesDto = foundEmployees.stream()
				.map(EmployeeMapper.INSTANCE::employeeToEmployeeDto)
				.collect(Collectors.toList());

		return ResponseEntity.ok(foundEmployeesDto);
	}

	@Override
	@ReadEmployeeRole
	public ResponseEntity<EmployeeDto> findById(Long id) {
		LOGGER.info("Employee find: id={}", id);

		var foundEmployee = repository.findById(id);

		EmployeeDto foundEmployeeDto = new EmployeeDto();
		if (foundEmployee != null) {
			foundEmployeeDto = EmployeeMapper.INSTANCE
					.employeeToEmployeeDto(foundEmployee);
		}

		return ResponseEntity.ok(foundEmployeeDto);
	}

	@Override
	@QueryEmployeeRole
	public ResponseEntity<List<EmployeeDto>> findByOrganization(Long organizationId) {
		LOGGER.info("Employee find: organizationId={}", organizationId);

		var foundEmployees = repository.findByOrganization(organizationId);

		var foundEmployeesDto = foundEmployees.stream()
				.map(EmployeeMapper.INSTANCE::employeeToEmployeeDto)
				.collect(Collectors.toList());

		return ResponseEntity.ok(foundEmployeesDto);
	}
}
