package com.rs.ecommerce.orderservice.bootstrap;

import com.rs.ecommerce.orderservice.model.Order;
import com.rs.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderInitiailizer implements CommandLineRunner {

    private final OrderRepository orderRepository;

    private static final List<Order> orderList = new ArrayList<>();

    static{
        orderList.add(new Order("1","P100",100.0,101l));
        orderList.add(new Order("2","P200",110.0,121l));
        orderList.add(new Order("3","P201",120.0,123l));
        orderList.add(new Order("4","P202",130.0,126l));
        orderList.add(new Order("5","P203",210.0,151l));
    }

    @Override
    public void run(String... args) throws Exception {
        orderRepository.deleteAll()
                .thenMany(Flux.fromIterable(orderList)
                        .flatMap(orderRepository::save)
                ).subscribe(null,null, ()-> {
                    orderRepository.findAll().subscribe(System.out::println);
                });

    }
}
