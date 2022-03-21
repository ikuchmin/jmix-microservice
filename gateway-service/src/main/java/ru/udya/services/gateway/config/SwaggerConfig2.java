package ru.udya.services.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EnableSwagger2
@Configuration
public class SwaggerConfig2 {

    @Autowired
    RouteDefinitionLocator locator;

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider(
            InMemorySwaggerResourcesProvider defaultResourcesProvider) {
        SwaggerResourcesProvider provider = () -> setResources();
        System.out.println(provider.get());
        return provider;
    }

    private List<SwaggerResource> setResources() {
        List<SwaggerResource> resources = new ArrayList<>();
        locator.getRouteDefinitions().subscribe(routeDefinition -> routeDefinition.getPredicates()
                .forEach(predicateDefinition -> {
                    String namePrefix = predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0");
                    if(!Objects.isNull(namePrefix)) {
                        String nameResource = namePrefix.replace("/**",  "/v2/api-docs");
                        resources.add(swaggerResource(routeDefinition.getId(), nameResource));
                    }
                }));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        return swaggerResource;
    }

}
