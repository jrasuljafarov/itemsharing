package com.itemsharing.itemservice.controller;

import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.service.ItemService;
import com.itemsharing.itemservice.service.UserService;
import com.itemsharing.itemservice.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/item")
@Slf4j
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @PostMapping("{username}")
    public Item addItemByUser(@RequestBody Item item, @PathVariable String username) {
        return itemService.addItemByUser(item, username);
    }

    @GetMapping("username/{username}")
    public List<Item> getItemByUsername(@PathVariable String username) {
        return itemService.getItemsByUsername(username);
    }

    @GetMapping("/user")
    public User findUserByUsername(@RequestParam String username) {
        log.debug("ItemController correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return userService.getUserByUserName(username);
    }


}
