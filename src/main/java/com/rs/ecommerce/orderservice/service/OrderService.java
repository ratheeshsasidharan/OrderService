package com.rs.ecommerce.orderservice.service;

import com.rs.ecommerce.orderservice.model.Order;
import com.rs.ecommerce.orderservice.model.OrderEvent;
import com.rs.ecommerce.orderservice.model.OrderEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<Order>  getOrderById(String id);
    Flux<Order> getAllOrders();

    Flux<OrderEvent> emitOrderEvent(String orderId);
}
