package org.example.h13_spring_boot.controller;

import org.example.h13_spring_boot.dto.OrderDTO;
import org.example.h13_spring_boot.dto.OrderDetailDTO;
import org.example.h13_spring_boot.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin(origins = "http://localhost:63342", allowedHeaders = "*")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("place")
    public String placeOrder(@RequestBody OrderDTO orderDTO) {
        orderService.placeOrder(orderDTO);
        return "Order placed successfully";
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/nextId")
    public ResponseEntity<String> getNextOrderId() {
        return ResponseEntity.ok(orderService.generateNextOrderId());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
}
