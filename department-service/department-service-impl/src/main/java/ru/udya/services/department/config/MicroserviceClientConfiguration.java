package ru.udya.services.department.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;
import ru.udya.services.employee.api.config.EmployeeApiClientConfiguration;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@EnableEurekaClient
@Import(EmployeeApiClientConfiguration.class)
@EnableFeignClients(basePackages = {"ru.udya.services.department"})
@Configuration
public class MicroserviceClientConfiguration {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

}
