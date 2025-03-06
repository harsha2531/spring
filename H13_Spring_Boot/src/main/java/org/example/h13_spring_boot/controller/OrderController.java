package org.example.h13_spring_boot.controller;

import org.example.h13_spring_boot.dto.OrderRequestDTO;
import org.example.h13_spring_boot.entity.OrderDetail;
import org.example.h13_spring_boot.entity.Orders;
import org.example.h13_spring_boot.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin(origins = "http://localhost:63342" ,allowedHeaders = "*")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDTO orderRequest) {
        try {
            System.out.println("Received OrderRequest: " + orderRequest);
            orderService.placeOrder(orderRequest.getOrder(),orderRequest.getOrderDetails());
            return ResponseEntity.ok("Order placed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.ok("Order failed: " + e.getMessage());
        }
    }
    @GetMapping("getAll")
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();  // Ensure this returns a List<Orders>
    }
}
