package org.example.h13_spring_boot.service.impl;

import org.example.h13_spring_boot.dto.ItemDTO;
import org.example.h13_spring_boot.entity.Item;
import org.example.h13_spring_boot.repo.ItemRepo;
import org.example.h13_spring_boot.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Override
    public boolean addItem(ItemDTO itemDTO) {
        Item item = new Item(
                itemDTO.getId(),
                itemDTO.getName(),
                itemDTO.getPrice(),
                itemDTO.getQtyOnHand());
        itemRepo.save(item);
        return true;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @Override
    public Item updateItem(ItemDTO itemDTO){
        Item item = itemRepo.findById(itemDTO.getId())
                .orElseThrow(() -> new RuntimeException("Item not found with id " + itemDTO.getId()));
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setQtyOnHand(itemDTO.getQtyOnHand());
        return itemRepo.save(item);
    }

    @Override
    public void deleteItem(int id){
        Item item = itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id " + id));
        itemRepo.delete(item);
    }
}
