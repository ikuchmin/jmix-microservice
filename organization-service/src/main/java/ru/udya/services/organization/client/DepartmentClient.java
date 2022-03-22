package ru.udya.services.organization.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.udya.services.organization.model.Department;

import java.util.List;

@FeignClient(name = "department-service", path = "/rest/public")
public interface DepartmentClient {

	@GetMapping("/organization/{organizationId}")
    List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId);
	
	@GetMapping("/organization/{organizationId}/with-employees")
    List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId);
	
}
