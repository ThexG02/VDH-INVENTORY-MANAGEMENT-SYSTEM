package com.microservices.INVENTRY.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private List<OrderItemDto> item;
}
