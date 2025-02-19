package org.example.h13_spring_boot.service;

import org.example.h13_spring_boot.dto.CustomerDTO;
import org.example.h13_spring_boot.dto.ItemDTO;
import org.example.h13_spring_boot.entity.Customer;
import org.example.h13_spring_boot.entity.Item;
import org.example.h13_spring_boot.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepo itemRepo;

    public boolean addItem(ItemDTO itemDTO) {
        Item item = new Item(
                itemDTO.getId(),
                itemDTO.getName(),
                itemDTO.getPrice());
        itemRepo.save(item);
        return true;
    }

    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    public Item updateItem(int id, ItemDTO itemDTO){
        Item item = itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id " + id));
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        return itemRepo.save(item);
    }

    public void deleteItem(int id){
        Item item = itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id " + id));
        itemRepo.delete(item);
    }
}
