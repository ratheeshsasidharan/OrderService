package com.rs.ecommerce.orderservice.service;


import com.rs.ecommerce.orderservice.model.ProductDto;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductDto> findById(String id);

}
