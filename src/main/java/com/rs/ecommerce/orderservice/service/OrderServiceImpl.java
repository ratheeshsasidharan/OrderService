package com.rs.ecommerce.orderservice.service;

import com.rs.ecommerce.orderservice.model.Order;
import com.rs.ecommerce.orderservice.model.OrderEvent;
import com.rs.ecommerce.orderservice.model.OrderStatus;
import com.rs.ecommerce.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

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
}
