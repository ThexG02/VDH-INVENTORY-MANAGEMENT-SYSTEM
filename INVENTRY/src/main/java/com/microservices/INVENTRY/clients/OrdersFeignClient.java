package com.microservices.INVENTRY.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="Orders", path = "/orders")
public interface OrdersFeignClient {
    @GetMapping("/core/helloOrders")
    String helloOrders();
}
