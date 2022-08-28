package com.rs.ecommerce.orderservice.controller;

import com.rs.ecommerce.orderservice.model.Order;
import com.rs.ecommerce.orderservice.model.OrderEvent;
import com.rs.ecommerce.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderResource {

    private final OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<Flux<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Order>> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/test")
    public ResponseEntity<Mono<Order>> saveOrder(@Valid @RequestBody Order order){
        return ResponseEntity.ok(orderService.saveOrUpdateOrder(order));
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    ResponseEntity<Flux<OrderEvent>> streamOrderEvents(@PathVariable String id) {
        return ResponseEntity.ok(orderService.emitOrderEvent(id));
    }
}
