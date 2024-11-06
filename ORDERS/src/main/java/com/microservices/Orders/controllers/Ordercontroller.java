package com.microservices.Orders.controllers;

import com.microservices.Orders.dto.OrderRequestDto;
import com.microservices.Orders.dto.ReStockItemDto;
import com.microservices.Orders.dto.ReStockRequestDto;
import com.microservices.Orders.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class Ordercontroller {

    private final OrderService orderService;

    @GetMapping("/helloOrders")
    public String helloOrders(){return "Hello from Orders Service";}

    @PostMapping("/create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderRequestDto orderRequestDto1 = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(orderRequestDto1);
    }

    @PostMapping("/cancel-order")
    public ResponseEntity<OrderRequestDto> cancelOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderRequestDto orderRequestDto1 = orderService.cancelOrder(orderRequestDto);
        return ResponseEntity.ok(orderRequestDto1);
    }

    @PostMapping("/restock")
    public ResponseEntity<ReStockRequestDto> restock(@RequestBody ReStockRequestDto reStockRequestDto){
        ReStockRequestDto reStockRequestDto1 = orderService.restockOrder(reStockRequestDto);
        return ResponseEntity.ok(reStockRequestDto1);
    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders(HttpServletRequest httpServletRequest){
        log.info("Fetching all orders via controller");
        List<OrderRequestDto> orders = orderService.getAllOrders().toList();
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id){
        log.info("Fetching order with Id :{}",id);
        OrderRequestDto orderRequestDto= orderService.getorderByTd(id);
        return ResponseEntity.ok(orderRequestDto);
    }


}
