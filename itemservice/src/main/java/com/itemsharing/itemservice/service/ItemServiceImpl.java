package com.itemsharing.itemservice.service;

import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.repository.ItemRepository;
import com.itemsharing.itemservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public Item addItemByUser(Item item, String username) {
        Item localItem = itemRepository.findByName(item.getName());
        if (localItem != null) {
            log.info("Item with name {} already exists", localItem.getName());
        } else {
            User user = userRepository.findUserByUsername(username);
            item.setUser(user);
            return itemRepository.save(item);
        }
        return localItem;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> getItemsByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        return itemRepository.findByUser(user);
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
