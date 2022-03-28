package ru.udya.services.employee.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import ru.udya.services.employee.api.client.EmployeeApi;
import ru.udya.services.employee.api.invoker.ApiClient;

public class EmployeeApiClientConfiguration {

    @Bean
    EmployeeApi employeeApiClient(WebClient.Builder webClientBuilder) {
        var baseUrl = "http://employee-service/";
        var employeeWebClient = webClientBuilder.baseUrl(baseUrl).build();

        var employeeApiClient = new ApiClient(employeeWebClient);
        employeeApiClient.setBasePath(baseUrl);

        return new EmployeeApi(employeeApiClient);
    }
}
