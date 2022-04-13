package ru.udya.services.gateway.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import ru.udya.services.gateway.api.client.DepartmentControllerApi;
import ru.udya.services.gateway.api.client.EmployeeControllerApi;
import ru.udya.services.gateway.api.client.OrganizationControllerApi;
import ru.udya.services.gateway.api.invoker.ApiClient;

public class GatewayApiClientConfiguration {

    private ApiClient apiClient;

    public GatewayApiClientConfiguration(WebClient.Builder webClientBuilder) {
        var baseUrl = "http://gateway-service/";

        var webClient = webClientBuilder.baseUrl(baseUrl).build();
        var apiClient = new ApiClient(webClient);
        apiClient.setBasePath(baseUrl);

        this.apiClient = apiClient;
    }

    @Bean
    EmployeeControllerApi employeeControllerApi() {
        return new EmployeeControllerApi(apiClient);
    }

    @Bean
    DepartmentControllerApi departmentControllerApi() {
        return new DepartmentControllerApi(apiClient);
    }

    @Bean
    OrganizationControllerApi organizationControllerApi() {
        return new OrganizationControllerApi(apiClient);
    }
}
