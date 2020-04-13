package com.itemsharing.itemservice.client;

import com.itemsharing.itemservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userservice")
public interface UserClient {

    @GetMapping("/v1/user/{username}")
    User findUserByUsername(@PathVariable String username);

}
