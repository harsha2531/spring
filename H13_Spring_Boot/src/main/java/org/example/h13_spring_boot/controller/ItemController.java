package org.example.h13_spring_boot.controller;

import org.example.h13_spring_boot.dto.CustomerDTO;
import org.example.h13_spring_boot.dto.ItemDTO;
import org.example.h13_spring_boot.entity.Customer;
import org.example.h13_spring_boot.entity.Item;
import org.example.h13_spring_boot.repo.ItemRepo;
import org.example.h13_spring_boot.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepo itemRepo;

    @PostMapping("save")
    public boolean saveItem(@RequestBody ItemDTO itemDTO){
//        System.out.println(customerDTO.getName());
//        return customerDTO.toString();
        itemService.addItem(itemDTO);
        return true;
    }

    @GetMapping("getAll")
    public List<ItemDTO> getAllItems(){
        List<Item> items = itemService.getAllItems();
        return items.stream().map(item -> new ItemDTO(
                item.getId(),
                item.getName(),
                item.getPrice()
        )).collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public ItemDTO updateItem(@PathVariable int id, @RequestBody ItemDTO itemDTO){
        Item updatedItem = itemService.updateItem(id, itemDTO);
        return new ItemDTO(
                updatedItem.getId(),
                updatedItem.getName(),
                updatedItem.getPrice()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable int id){
        itemService.deleteItem(id);
        return ResponseEntity.ok("Item deleted with id: " + id + " successfully");
    }

}
