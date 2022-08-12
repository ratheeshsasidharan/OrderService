package com.rs.ecommerce.orderservice.repository;

import com.rs.ecommerce.orderservice.model.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderRepository extends ReactiveMongoRepository<Order,String> {
}
