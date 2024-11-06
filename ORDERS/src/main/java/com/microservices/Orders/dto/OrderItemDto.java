package com.microservices.Orders.dto;

import lombok.Data;

@Data
public class OrderItemDto {
private Long id;
private Long productid;
private Integer quantity;
}
