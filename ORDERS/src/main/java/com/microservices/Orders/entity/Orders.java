package com.microservices.Orders.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Double total_cart_price;

    @OneToMany(mappedBy = "Order", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> item;
}
