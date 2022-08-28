package com.rs.ecommerce.orderservice.service;

import com.rs.ecommerce.orderservice.model.Order;
import com.rs.ecommerce.orderservice.model.OrderEvent;
import com.rs.ecommerce.orderservice.model.OrderStatus;
import com.rs.ecommerce.orderservice.model.ProductDto;
import com.rs.ecommerce.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductProxy productProxy;

    private final WebClient webClient;

    @Override
    public Mono<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Flux<OrderEvent> emitOrderEvent(String orderId) {
        return Flux.<OrderEvent>generate(orderEventsSynchronousSink -> orderEventsSynchronousSink.next(
                new OrderEvent("1", orderId, OrderStatus.CREATED, OrderStatus.PAYMENT_PROGRESS, Instant.now())
        )).delayElements(Duration.ofSeconds(1));
    }

    @Override
    public Mono<Order> saveOrUpdateOrder(Order order) {
        //ProductDto product = productProxy.getProductById("62f09c19abdca87a0a04c0b2");
        Mono<ProductDto> product = webClient.get().uri("http://product-service/products/62f09c19abdca87a0a04c0b2")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProductDto.class);
        product.doOnNext(productDto -> System.out.println(productDto)).subscribe();
        return orderRepository.insert(order);
    }
}
