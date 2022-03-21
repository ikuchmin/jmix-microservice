package ru.udya.services.gateway.config;//package br.com.services.gateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@EnableSwagger2
//@Configuration
//public class SwaggerConfig {
//    @Primary
//    @Bean
//    public SwaggerResourcesProvider swaggerResourcesProvider(
//            InMemorySwaggerResourcesProvider defaultResourcesProvider) {
//
//        SwaggerResourcesProvider provider = () -> setResources();
//
//        System.out.println(provider.get());
//        return provider;
//
//    }
//
//    private List<SwaggerResource> setResources() {
//        List<SwaggerResource> resources = new ArrayList<>();
//
//        SwaggerResource wsResource = new SwaggerResource();
//        wsResource.setName("API Microservices");
//        wsResource.setSwaggerVersion("2.0");
//        wsResource.setLocation("/swagger-apis/v1/swagger.yaml");
//        resources.add(wsResource);
//
//        return resources;
//    }
//}
