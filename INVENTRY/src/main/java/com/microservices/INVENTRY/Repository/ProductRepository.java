package com.microservices.INVENTRY.Repository;

import com.microservices.INVENTRY.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product , Long> {
}
