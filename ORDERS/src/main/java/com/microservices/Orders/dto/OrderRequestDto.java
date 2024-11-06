package com.microservices.Orders.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private Long id;
    private Double total_cart_price;
    private List<OrderItemDto> item;
}
