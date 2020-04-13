package com.itemsharing.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class ItemserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemserviceApplication.class, args);
    }

}
