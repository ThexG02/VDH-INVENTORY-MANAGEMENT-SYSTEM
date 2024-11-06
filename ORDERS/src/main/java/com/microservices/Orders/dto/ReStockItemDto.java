package com.microservices.Orders.dto;

import lombok.Data;

@Data
public class ReStockItemDto {
    private  Long id;
    private Long productid;
    private Integer quantity;
}
