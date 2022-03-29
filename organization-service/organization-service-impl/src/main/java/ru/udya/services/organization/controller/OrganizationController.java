package ru.udya.services.organization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.udya.services.department.api.client.DepartmentApiClient;
import ru.udya.services.department.api.model.DepartmentDto;
import ru.udya.services.employee.api.client.EmployeeApiClient;
import ru.udya.services.organization.api.controller.OrganizationApi;
import ru.udya.services.organization.api.model.OrganizationDto;
import ru.udya.services.organization.client.DepartmentClient;
import ru.udya.services.organization.controller.mapper.DepartmentMapper;
import ru.udya.services.organization.controller.mapper.EmployeeMapper;
import ru.udya.services.organization.controller.mapper.OrganizationMapper;
import ru.udya.services.organization.model.Department;
import ru.udya.services.organization.model.Employee;
import ru.udya.services.organization.model.Organization;
import ru.udya.services.organization.repository.OrganizationRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrganizationController implements OrganizationApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	OrganizationRepository repository;

	@Autowired
	DepartmentApiClient departmentClient;

	@Autowired
	EmployeeApiClient employeeClient;

	@Override
	public ResponseEntity<OrganizationDto> create(OrganizationDto organizationDto) {
		LOGGER.info("Organization add: {}", organizationDto);

		var organization = OrganizationMapper.INSTANCE.organizationDtoToOrganization(organizationDto);

		var savedOrganization = repository.add(organization);

		var savedOrganizationDto = OrganizationMapper.INSTANCE.organizationToOrganizationDto(savedOrganization);

		return ResponseEntity.ok(savedOrganizationDto);
	}

	@Override
	public ResponseEntity<List<OrganizationDto>> findAll() {
		LOGGER.info("Organization find");

		var foundOrganizations = repository.findAll();

		var foundOrganizationDtos = foundOrganizations.stream()
				.map(OrganizationMapper.INSTANCE::organizationToOrganizationDto)
				.collect(Collectors.toList());

		return ResponseEntity.ok(foundOrganizationDtos);
	}

	@Override
	public ResponseEntity<OrganizationDto> findById(Long id) {
		LOGGER.info("Organization find: id={}", id);

		var foundOrganization = repository.findById(id);

		OrganizationDto foundOrganizationDto = new OrganizationDto();
		if (foundOrganization != null) {
			foundOrganizationDto = OrganizationMapper.INSTANCE
					.organizationToOrganizationDto(foundOrganization);
		}

		return ResponseEntity.ok(foundOrganizationDto);
	}

	@Override
	public ResponseEntity<OrganizationDto> findByIdWithEmployees(Long id) {
		LOGGER.info("Organization find: id={}", id);
		Organization organization = repository.findById(id);

		var foundEmployeesResponse = employeeClient.findByOrganization(organization.getId());

		//noinspection ConstantConditions
		List<Employee> foundEmployees = foundEmployeesResponse.getBody().stream()
					.map(EmployeeMapper.INSTANCE::employeeDtoToEmployee)
					.collect(Collectors.toList());

		organization.setEmployees(foundEmployees);

		var organizationDto = OrganizationMapper.INSTANCE
				.organizationToOrganizationDto(organization);

		return ResponseEntity.ok(organizationDto);
	}

	@Override
	public ResponseEntity<OrganizationDto> findByIdWithDepartments(Long id) {
		LOGGER.info("Organization find: id={}", id);

		var foundOrganization = repository.findById(id);

		if (foundOrganization == null) {
			return ResponseEntity.ok(new OrganizationDto());
		}

		var foundDepartmentsResponse = departmentClient.findByOrganization(foundOrganization.getId());

		//noinspection ConstantConditions
		var foundDepartments = foundDepartmentsResponse.getBody().stream()
				.map(DepartmentMapper.INSTANCE::departmentDtoToDepartment)
				.collect(Collectors.toList());

		foundOrganization.setDepartments(foundDepartments);

		var foundOrganizationDto = OrganizationMapper.INSTANCE.organizationToOrganizationDto(foundOrganization);

		return ResponseEntity.ok(foundOrganizationDto);
	}

	@Override
	public ResponseEntity<OrganizationDto> findByIdWithDepartmentsAndEmployees(Long id) {
		LOGGER.info("Organization find: id={}", id);

		var foundOrganization = repository.findById(id);

		if (foundOrganization == null) {
			return ResponseEntity.ok(new OrganizationDto());
		}

		var foundDepartmentsResponse = departmentClient
				.findByOrganizationWithEmployees(foundOrganization.getId());

		//noinspection ConstantConditions
		var foundDepartments = foundDepartmentsResponse.getBody().stream()
				.map(DepartmentMapper.INSTANCE::departmentDtoToDepartment)
				.collect(Collectors.toList());

		foundOrganization.setDepartments(foundDepartments);

		var foundOrganizationDto = OrganizationMapper.INSTANCE
				.organizationToOrganizationDto(foundOrganization);

		return ResponseEntity.ok(foundOrganizationDto);
	}

	//	@PostMapping
//	public Organization add(@RequestBody Organization organization) {
//		LOGGER.info("Organization add: {}", organization);
//		return repository.add(organization);
//	}
//
//	@GetMapping
//	public List<Organization> findAll() {
//		LOGGER.info("Organization find");
//		return repository.findAll();
//	}
//
//	@GetMapping("/{id}")
//	public Organization findById(@PathVariable("id") Long id) {
//		LOGGER.info("Organization find: id={}", id);
//		return repository.findById(id);
//	}
//
//	@GetMapping("/{id}/with-departments")
//	public Organization findByIdWithDepartments(@PathVariable("id") Long id) {
//		LOGGER.info("Organization find: id={}", id);
//		Organization organization = repository.findById(id);
//		organization.setDepartments(departmentClient.findByOrganization(organization.getId()));
//		return organization;
//	}
//
//	@GetMapping("/{id}/with-departments-and-employees")
//	public Organization findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
//		LOGGER.info("Organization find: id={}", id);
//		Organization organization = repository.findById(id);
//		organization.setDepartments(departmentClient.findByOrganizationWithEmployees(organization.getId()));
//		return organization;
//	}
//
//	@GetMapping("/{id}/with-employees")
//	public Organization findByIdWithEmployees(@PathVariable("id") Long id) {
//		LOGGER.info("Organization find: id={}", id);
//		Organization organization = repository.findById(id);
//
//		var foundEmployeesResponse = employeeClient.findByOrganization(organization.getId());
//
//		//noinspection ConstantConditions
//		List<Employee> foundEmployees = foundEmployeesResponse.getBody().stream()
//					.map(EmployeeMapper.INSTANCE::employeeDtoToEmployee)
//					.collect(Collectors.toList());
//
//		organization.setEmployees(foundEmployees);
//
//		return organization;
//	}
	
}
