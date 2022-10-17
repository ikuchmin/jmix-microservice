package ru.udya.services.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import ru.udya.services.organization.model.Organization;
import ru.udya.services.organization.repository.OrganizationRepository;

//@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class OrganizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationApplication.class, args);
    }

    @Bean
    OrganizationRepository repository() {
        OrganizationRepository repository = new OrganizationRepository();
        repository.add(new Organization("Microsoft", "Redmond, Washington, USA"));
        repository.add(new Organization("Oracle", "Redwood City, California, USA"));
        return repository;
    }
}
