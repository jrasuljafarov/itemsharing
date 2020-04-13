package com.itemsharing.itemservice.repository;

import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByName(String name);

    List<Item> findByUser(User user);
}
