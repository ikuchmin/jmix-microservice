package ru.udya.services.employee.api.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "employee-service")
public interface EmployeeApiClient extends EmployeeApi {
}
