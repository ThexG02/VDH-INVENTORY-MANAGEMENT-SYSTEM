package com.microservices.Orders.clients;

import com.microservices.Orders.dto.OrderRequestDto;
import com.microservices.Orders.dto.ReStockRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "INVENTRY", path="/inventory")public interface InventoryOpenFeignClient {

        @PutMapping("/products/reduce-stocks")
        Double reduceStocks(@RequestBody OrderRequestDto orderRequestDto);

        @PutMapping("/products/add-stocks")
        Double addStocks(@RequestBody OrderRequestDto orderRequestDto);

        @PutMapping("/products/restock")
        Double reStock(@RequestBody ReStockRequestDto reStockRequestDto);



}
