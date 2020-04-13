package com.itemsharing.itemservice.service;

import com.itemsharing.itemservice.client.UserClient;
import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.util.UserContextHolder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserClient userClient;

    @Override
    @HystrixCommand(
            fallbackMethod = "fallbackUser",
            threadPoolKey = "itemByUserThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value="30"),
                    @HystrixProperty(name="maxQueueSize",value = "10")
            }
//            commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "12000")
//    }
    )
    public User getUserByUserName(String username) {
        log.info("findUser by username: {}", username);
        latency();
        log.debug("ItemService.getUserByUsername Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return userClient.findUserByUsername(username);
    }

    private void latency() {
        int rdm = new Random().nextInt(3) + 1;
        if (rdm == 3) {
            try {
                Thread.sleep(11000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private User fallbackUser(String username) {
        User user = new User();
        user.setUserId(213);
        user.setFirstName("Test user");
        return user;
    }

}
