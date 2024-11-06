package com.microservices.INVENTRY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InventryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventryApplication.class, args);
	}

}
