package ru.udya.services.department.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.udya.services.department.api.client.DepartmentApi;

@FeignClient(name = "department-service")
public interface DepartmentApiClient extends DepartmentApi {
}
