package com.rs.ecommerce.orderservice.controller;

import com.rs.ecommerce.orderservice.model.Order;
import com.rs.ecommerce.orderservice.model.OrderEvent;
import com.rs.ecommerce.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderResource {

    private final OrderService orderService;

    @GetMapping("/{id}")
    Mono<Order> getOrderById(@PathVariable String id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/")
    Flux<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<OrderEvent> streamOrderEvents(@PathVariable String id){
        return orderService.emitOrderEvent(id);
    }
}
