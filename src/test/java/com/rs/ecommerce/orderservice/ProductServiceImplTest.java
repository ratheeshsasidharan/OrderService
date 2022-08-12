package com.rs.ecommerce.orderservice;

import com.rs.ecommerce.orderservice.config.WebClientConfig;
import com.rs.ecommerce.orderservice.model.ProductDto;
import com.rs.ecommerce.orderservice.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class ProductServiceImplTest {

    ProductServiceImpl productService;

    @BeforeEach
    void setup(){
        productService= new ProductServiceImpl(new WebClientConfig().webClient());
    }

    @Test
    void testFindById(){
        Mono<ProductDto> productDtoMono= productService.findById("62f09c19abdca87a0a04c0b2");
        ProductDto productDto = productDtoMono.block();
        System.out.println(productDto);
        Assertions.assertNotNull(productDto);
        Assertions.assertNotNull(productDto.getProductName());
    }


}
