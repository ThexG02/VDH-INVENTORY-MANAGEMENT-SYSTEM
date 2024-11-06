package com.microservices.Orders.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReStockRequestDto {
    private Long id;
    private Double total_ReStock_Amount;
    private List<ReStockItemDto> item;
}
