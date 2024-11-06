package com.microservices.INVENTRY.dto;

import lombok.Data;

import java.util.List;

@Data
public class RestockRequestDto {
    private List<ReStockItemDto> itemList;
}
