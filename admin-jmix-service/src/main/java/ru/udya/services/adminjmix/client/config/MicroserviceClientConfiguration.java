package ru.udya.services.adminjmix.client.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.security.oauth2.server.resource.web.reactive.function.client.ServerBearerExchangeFilterFunction;
import org.springframework.security.oauth2.server.resource.web.reactive.function.client.ServletBearerExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import ru.udya.services.gateway.api.config.GatewayApiClientConfiguration;
import ru.udya.services.adminjmix.client.webclient.JmixServletBearerExchangeFilterFunction;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration(proxyBeanMethods = false)
@Import(GatewayApiClientConfiguration.class)
@EnableFeignClients(basePackages = {"ru.udya.services.adminjmix"})
public class MicroserviceClientConfiguration {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        var serverBearer = new ServerBearerExchangeFilterFunction();
        var servletBearer = new ServletBearerExchangeFilterFunction();
        var jmixBearer = new JmixServletBearerExchangeFilterFunction();

        return WebClient.builder()
                .filter(jmixBearer)
                .filter(servletBearer)
                .filter(serverBearer); // relay auth token
    }
}
