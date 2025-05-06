package com.queue_management.shopservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    @LoadBalanced // for resolving using the service names instead of hardcoded urls and ip addresses
    // for registering a webclient.builder bean that is load balanced
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }

    @Bean
    // to create an instance of webclient that is pre-configured to connect to user-service. Built on top of
    // the registered webclient.builder bean
    public WebClient userServiceWebClient(WebClient.Builder webClientBuilder){
        return webClientBuilder.baseUrl("http://USER-SERVICE").build();
    }
}
