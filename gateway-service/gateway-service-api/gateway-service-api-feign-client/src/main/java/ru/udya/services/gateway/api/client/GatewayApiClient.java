package ru.udya.services.gateway.api.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "gateway-service")
public interface GatewayApiClient {
}
