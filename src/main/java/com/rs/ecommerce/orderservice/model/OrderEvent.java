package com.rs.ecommerce.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class OrderEvent {

    private String orderId;
    private String id;
    private OrderStatus oldOrderStatus;
    private OrderStatus newOrderStatus;
    private Instant timestamp;
}
