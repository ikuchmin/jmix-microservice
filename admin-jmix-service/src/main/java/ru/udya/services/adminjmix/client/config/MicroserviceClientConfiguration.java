package ru.udya.services.adminjmix.client.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.server.resource.web.reactive.function.client.ServerBearerExchangeFilterFunction;
import org.springframework.security.oauth2.server.resource.web.reactive.function.client.ServletBearerExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import ru.udya.services.gateway.api.config.GatewayApiClientConfiguration;

import java.time.Duration;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration(proxyBeanMethods = false)
@Import(GatewayApiClientConfiguration.class)
public class MicroserviceClientConfiguration {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    @LoadBalanced
    public WebClient.Builder webClientBuilder(ClientRegistrationRepository clientRegistrations,
                                              OAuth2AuthorizedClientRepository authorizedClients) {

        HttpClient client = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5)); // change timeout for long first request

        var oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
                clientRegistrations, authorizedClients);
        oauth2.setDefaultOAuth2AuthorizedClient(true);

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(client))
                .apply(oauth2.oauth2Configuration()); // relay auth token
    }
}
