package com.example.advancedtransaction.controller;

import com.example.advancedtransaction.entity.Order;
import com.example.advancedtransaction.entity.Product;
import com.example.advancedtransaction.service.InventoryService;
import com.example.advancedtransaction.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final InventoryService inventoryService;

    public OrderController(OrderService orderService, InventoryService inventoryService) {
        this.orderService = orderService;
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Map<String, Object> request) {
        String customerEmail = (String) request.get("customerEmail");
        @SuppressWarnings("unchecked")
        Map<String, Integer> items = (Map<String, Integer>) request.get("items");

        Order order = orderService.createOrder(customerEmail, items);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{orderNumber}/process")
    public ResponseEntity<String> processOrder(@PathVariable String orderNumber) {
        orderService.processOrder(orderNumber);
        return ResponseEntity.ok("Order processed successfully");
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderNumber) {
        return ResponseEntity.ok(orderService.getOrder(orderNumber));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(inventoryService.getAllProducts());
    }
}