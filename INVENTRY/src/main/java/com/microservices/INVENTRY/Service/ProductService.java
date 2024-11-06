package com.microservices.INVENTRY.Service;

import com.microservices.INVENTRY.Repository.ProductRepository;
import com.microservices.INVENTRY.dto.*;
import com.microservices.INVENTRY.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAllInventory (){
        log.info("Fetching All inventory items");
        List<Product> inventories = productRepository.findAll();
        return inventories.stream().map(product -> modelMapper.map(product,ProductDto.class))
                .toList();
    }

    public ProductDto getprtoductById(Long id){
        log.info("Fetching the product by iod :{}",id);
        Optional<Product> inventory = productRepository.findById(id);
        return inventory
                .map(product -> modelMapper.map(product, ProductDto.class))
                .orElseThrow(()-> new RuntimeException("Product not found in the inventory "));
    }

    @Transactional
    public Double reduceStocks(OrderRequestDto orderRequestDto){
        log.info("Reducing the stocks");
        Double totalprice =0.0;
        for(OrderItemDto orderItemDto : orderRequestDto.getItem()){
            Long productId = orderItemDto.getProductid();
            Integer quantity = orderItemDto.getQuantity();

            Product product = productRepository.findById(productId).orElseThrow(()->
                    new RuntimeException("Product not found in the inventory"+productId));

            if(product.getStock()< quantity){
                throw new RuntimeException("Product cannot be fulfilled for given quantity");
            }

            product.setStock(product.getStock()-quantity);
            productRepository.save(product);
            totalprice+=quantity*product.getPrice();

        }
        return totalprice;
    }
    @Transactional
    public Double addStocks(OrderRequestDto orderRequestDto) {
        log.info("adding cancel item back to stocks");
        Double refund =0.0;
        for(OrderItemDto orderItemDto: orderRequestDto.getItem()){
            Long productId = orderItemDto.getProductid();
            Integer quantity = orderItemDto.getQuantity();

            Product product = productRepository.findById(productId).orElseThrow();
            product.setStock(product.getStock()+quantity);
            productRepository.save(product);
            refund+=quantity*product.getPrice();

        }
        return refund;
    }

    @Transactional
    public Double restock(RestockRequestDto restockRequestDto) {
        log.info("Restocking the orders according to the restock order");
        Double reStockAmount = 0.0;
        for(ReStockItemDto reStockItemDto: restockRequestDto.getItemList()){
            Long productId = reStockItemDto.getProductid();
            Integer quantity = reStockItemDto.getQuantity();

            Product product = productRepository.findById(productId).orElseThrow();
            product.setStock(product.getStock()+quantity);
            productRepository.save(product);
            reStockAmount+=quantity*product.getPrice();
        }
        return reStockAmount;
    }
}
