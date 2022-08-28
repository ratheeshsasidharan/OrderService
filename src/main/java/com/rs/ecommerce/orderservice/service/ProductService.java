package com.rs.ecommerce.orderservice.service;


import com.rs.ecommerce.orderservice.model.ProductDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductDto> findById(String id);

    Mono<ResponseEntity<Void>> saveProduct(ProductDto productDto);

}
