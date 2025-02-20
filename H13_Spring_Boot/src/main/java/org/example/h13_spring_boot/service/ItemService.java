package org.example.h13_spring_boot.service;

import org.example.h13_spring_boot.dto.ItemDTO;
import org.example.h13_spring_boot.entity.Item;

import java.util.List;

public interface ItemService {
    public boolean addItem(ItemDTO itemDTO);

    public List<Item> getAllItems();

    public Item updateItem(ItemDTO itemDTO);

    public void deleteItem(int id);
}