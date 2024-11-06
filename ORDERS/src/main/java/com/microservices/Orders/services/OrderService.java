package com.microservices.Orders.services;

import com.microservices.Orders.Repository.OrderRepository;
import com.microservices.Orders.clients.InventoryOpenFeignClient;
import com.microservices.Orders.dto.OrderRequestDto;
import com.microservices.Orders.dto.ReStockRequestDto;
import com.microservices.Orders.entity.OrderItem;
import com.microservices.Orders.entity.OrderStatus;
import com.microservices.Orders.entity.Orders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final InventoryOpenFeignClient inventoryOpenFeignClient;

    //Service Logic for a Method to Create a new order
    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
        log.info("Calling the create new order Method");
        Double totalprice = inventoryOpenFeignClient.reduceStocks(orderRequestDto);
        Orders orders = modelMapper.map(orderRequestDto,Orders.class);
        for(OrderItem orderItem:orders.getItem()){
            orderItem.setOrder(orders);
        }
        orders.setTotal_cart_price(totalprice);
        orders.setOrderStatus(OrderStatus.confirmed);
        Orders savedOrdr = orderRepository.save(orders);
        return modelMapper.map(savedOrdr,OrderRequestDto.class);
    }

    //Service Logic for a Method to cancel an Order
    public OrderRequestDto cancelOrder(OrderRequestDto orderRequestDto) {
        log.info("calling the cancel order Method");
       // Double totalPrice = inventoryOpenFeignClient.addStocks(orderRequestDto);
        Orders orders = modelMapper.map(orderRequestDto, Orders.class);
        for(OrderItem orderItem:orders.getItem()){
            orderItem.setOrder(orders);
        }
        orders.setOrderStatus(OrderStatus.cancelled);
        Orders savedordr = orderRepository.save(orders);
        return modelMapper.map(savedordr,OrderRequestDto.class);

    }

    //Service Logic for a Method to ReStock the inventory

    public ReStockRequestDto restockOrder(ReStockRequestDto reStockRequestDto) {
        log.info("calling the Restock method");
        Orders orders = modelMapper.map(reStockRequestDto,Orders.class);
        for(OrderItem orderItem:orders.getItem()){
            orderItem.setOrder(orders);
        }
        orders.setOrderStatus(OrderStatus.restocked);
        Orders savedordr = orderRepository.save(orders);
        return modelMapper.map(savedordr,ReStockRequestDto.class);


    }

    public Stream<OrderRequestDto> getAllOrders(){
        log.info("Fetching all Oders");

        List<Orders> orders = orderRepository.findAll();

        return orders.stream().map(
                order -> modelMapper.map(order,OrderRequestDto.class));
    }

    public OrderRequestDto getorderByTd(Long id){

        log.info("Fetching the order by id : {}" , id);
        Orders order = orderRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Order not found"));
        return modelMapper.map(order,OrderRequestDto.class);
    }

    public OrderRequestDto createFallback(OrderRequestDto orderRequestDto , Throwable throwable){
        log.info("Fallback occured due to :{}", throwable.getMessage());
        return new OrderRequestDto();
    }


}
