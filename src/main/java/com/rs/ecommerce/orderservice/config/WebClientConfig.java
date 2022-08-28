package com.rs.ecommerce.orderservice.config;

import io.netty.handler.logging.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Configuration
public class WebClientConfig {

    @Autowired
    private ReactorLoadBalancerExchangeFilterFunction lbFunction;

    @Bean
    @LoadBalanced
    public WebClient webClient() {
        return WebClient.builder()
                .filter(lbFunction)
                .build();
    }
}
