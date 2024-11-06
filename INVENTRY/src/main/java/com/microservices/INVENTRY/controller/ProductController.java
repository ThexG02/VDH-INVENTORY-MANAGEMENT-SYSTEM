package com.microservices.INVENTRY.controller;

import com.microservices.INVENTRY.Service.ProductService;
import com.microservices.INVENTRY.clients.OrdersFeignClient;
import com.microservices.INVENTRY.dto.OrderRequestDto;
import com.microservices.INVENTRY.dto.ProductDto;
import com.microservices.INVENTRY.dto.RestockRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final RestClient restClient;
    private final DiscoveryClient discoveryClient;
    private final OrdersFeignClient ordersFeignClient;

    @GetMapping("/fetchOrders")
    public String fetchfromOrderService(){
       // ServiceInstance orderservice =  discoveryClient.getInstances("Orders").getFirst();
//       return restClient.get()
//                .uri(orderservice.getUri()+"/orders/core/helloOrders")
//               .retrieve()
//               .body(String.class);
        return ordersFeignClient.helloOrders();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllInventory(){
        List<ProductDto> inventories = productService.getAllInventory();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id){
        ProductDto inventories = productService.getprtoductById(id);
        return ResponseEntity.ok(inventories);
    }

    @PutMapping("reduce-stocks")
    public ResponseEntity<Double> reduceStock(@RequestBody OrderRequestDto orderRequestDto){
        Double totalPrice = productService.reduceStocks(orderRequestDto);
        return ResponseEntity.ok(totalPrice);

    }

    @PutMapping("add-stocks")
    public ResponseEntity<Double> addStocks(@RequestBody OrderRequestDto orderRequestDto){
        Double refundPrice = productService.addStocks(orderRequestDto);

        return ResponseEntity.ok(refundPrice);
    }

    @PutMapping("Restock")
    public ResponseEntity<Double> restock(@RequestBody RestockRequestDto restockRequestDto){
        Double reStockAmount = productService.restock(restockRequestDto);
        return  ResponseEntity.ok(reStockAmount);
    }

}
