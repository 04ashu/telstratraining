package com.telstra.orderservice.controller;

import com.telstra.orderservice.dto.OrderRequestDTO;
import com.telstra.orderservice.dto.OrderResponseDTO;
import com.telstra.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestBody OrderRequestDTO requestDTO){
        return ResponseEntity.ok(orderService.placeOrder(requestDTO.getUserId(), requestDTO.getProductId()));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
        List<OrderResponseDTO> orders = orderService.getAllOrders();

        if(orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }
}
