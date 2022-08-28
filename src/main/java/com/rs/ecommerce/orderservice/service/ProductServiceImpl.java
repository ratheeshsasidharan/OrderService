package com.rs.ecommerce.orderservice.service;

import com.rs.ecommerce.orderservice.model.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final WebClient webClient;

    @Override
    public Mono<ProductDto> findById(String id) {
        return webClient.get()
                .uri(id)
                .retrieve()
                .bodyToMono(ProductDto.class);
    }

    @Override
    public Mono<ResponseEntity<Void>> saveProduct(ProductDto productDto) {
        return webClient.post()
                .body(BodyInserters.fromValue(productDto))
                .retrieve()
                .toBodilessEntity();
    }
}
