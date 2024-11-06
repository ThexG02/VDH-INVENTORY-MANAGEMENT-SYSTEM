package com.microservices.INVENTRY.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long productid;
    private Integer quantity;
}
