package com.itemsharing.zuulserver;

import brave.sampler.Sampler;
import com.itemsharing.zuulserver.util.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableZuulProxy
public class ZuulserverApplication {

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    @Bean
    public RestTemplate getRestTemplate() {

        RestTemplate restTemplate = new RestTemplate();
        List interceptors = restTemplate.getInterceptors();

        if (interceptors == null) {
            restTemplate.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            restTemplate.setInterceptors(interceptors);
        }
        return restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulserverApplication.class, args);
    }

}
