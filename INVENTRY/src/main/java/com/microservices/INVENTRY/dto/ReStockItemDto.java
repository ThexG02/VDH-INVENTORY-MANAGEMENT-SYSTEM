package com.microservices.INVENTRY.dto;

import lombok.Data;

@Data
public class ReStockItemDto {
    private Long productid;
    private Integer quantity;
}
