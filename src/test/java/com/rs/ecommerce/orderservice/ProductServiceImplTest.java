package com.rs.ecommerce.orderservice;

import com.rs.ecommerce.orderservice.config.WebClientConfig;
import com.rs.ecommerce.orderservice.model.ProductDto;
import com.rs.ecommerce.orderservice.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Arrays;

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

    @Test
    void testSaveProduct(){
        ProductDto prod = ProductDto.builder().productCode("TV100")
                .id("8989").tags(Arrays.asList("tag1","tag2")).productName("Samsung Flat 12").productPrice(1020.0).build();
        ResponseEntity responseEntity = productService.saveProduct(prod).block();
        System.out.println(responseEntity);
        Assertions.assertNotNull(responseEntity);
    }

    @Test
    void testSaveProductWithoutPrice(){
        ProductDto prod = ProductDto.builder().productCode("TV100")
                .id("8989").tags(Arrays.asList("tag1","tag2")).productName("Samsung Flat 12").build();
        Assertions.assertThrows(WebClientResponseException.class, ()->{
            ResponseEntity responseEntity = productService.saveProduct(prod).block();
        });
    }


    @Test
    void testSaveProductWithoutPriceHandleException(){
        ProductDto prod = ProductDto.builder().productCode("TV100")
                .id("8989").tags(Arrays.asList("tag1","tag2")).productName("Samsung Flat 12").build();
        Mono<ResponseEntity<Void>> responseEntityMono = productService.saveProduct(prod);

        ResponseEntity<Void> responseEntity = responseEntityMono.onErrorResume(throwable -> {
            if(throwable instanceof WebClientResponseException){
                WebClientResponseException exception = (WebClientResponseException) throwable;
                return Mono.just(ResponseEntity.status(exception.getStatusCode()).build());
            } else {
                throw new RuntimeException(throwable);
            }
        }).block();
        System.out.println(responseEntity);
        Assertions.assertEquals(responseEntity.getStatusCode(),HttpStatus.BAD_REQUEST);
    }
}
