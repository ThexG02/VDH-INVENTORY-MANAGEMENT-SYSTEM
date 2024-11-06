package com.microservices.Orders.Repository;

import com.microservices.Orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long > {
}
