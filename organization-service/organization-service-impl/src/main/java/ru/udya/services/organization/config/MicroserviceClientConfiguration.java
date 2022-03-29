package ru.udya.services.organization.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"ru.udya.services.organization", "ru.udya.services.employee", "ru.udya.services.department"})
public class MicroserviceClientConfiguration {

}
