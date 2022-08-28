package com.rs.ecommerce.orderservice.service;

import com.rs.ecommerce.orderservice.model.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="product-service")
public interface ProductProxy {

    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}", consumes = "application/json")
    public ProductDto getProductById(@PathVariable("id") String id);
}
