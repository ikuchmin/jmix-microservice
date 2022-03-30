package ru.udya.services.gateway.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import ru.udya.services.gateway.api.client.DepartmentControllerApi;
import ru.udya.services.gateway.api.client.EmployeeControllerApi;
import ru.udya.services.gateway.api.client.OrganizationControllerApi;
import ru.udya.services.gateway.api.invoker.ApiClient;

public class GatewayApiClientConfiguration {

    @Bean
    EmployeeControllerApi employeeControllerApi(WebClient.Builder webClientBuilder) {
        var baseUrl = "http://employee-service/";
        var employeeWebClient = webClientBuilder.baseUrl(baseUrl).build();

        var employeeApiClient = new ApiClient(employeeWebClient);
        employeeApiClient.setBasePath(baseUrl);

        return new EmployeeControllerApi(employeeApiClient);
    }

    @Bean
    DepartmentControllerApi departmentControllerApi(WebClient.Builder webClientBuilder) {
        var baseUrl = "http://department-service/";
        var departmentClient = webClientBuilder.baseUrl(baseUrl).build();

        var employeeApiClient = new ApiClient(departmentClient);
        employeeApiClient.setBasePath(baseUrl);

        return new DepartmentControllerApi(employeeApiClient);
    }

    @Bean
    OrganizationControllerApi organizationControllerApi(WebClient.Builder webClientBuilder) {
        var baseUrl = "http://organization-service/";
        var organizationWebClient = webClientBuilder.baseUrl(baseUrl).build();

        var employeeApiClient = new ApiClient(organizationWebClient);
        employeeApiClient.setBasePath(baseUrl);

        return new OrganizationControllerApi(employeeApiClient);
    }
}
