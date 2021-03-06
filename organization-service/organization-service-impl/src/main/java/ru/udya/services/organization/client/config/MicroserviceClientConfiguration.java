package ru.udya.services.organization.client.config;

import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
@EnableFeignClients(basePackages = {"ru.udya.services.organization", "ru.udya.services.employee", "ru.udya.services.department"})
public class MicroserviceClientConfiguration {


    /**
     * See post https://www.springcloud.io/post/2022-01/feign-token-relay/
     * @return
     */
    @Bean
    @ConditionalOnClass({Feign.class})
    @ConditionalOnProperty(value = "feign.oauth2.enabled")
    RequestInterceptor bearerTokenRequestInterceptor() {
        return template -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
                String tokenValue = jwtAuthenticationToken.getToken().getTokenValue();
                template.header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenValue);
            }
        };
    }
}
