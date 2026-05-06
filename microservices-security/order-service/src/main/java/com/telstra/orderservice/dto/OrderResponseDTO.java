package com.telstra.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponseDTO {

    private Long orderId;
    private String userName;
    private String productName;
    private double price;
}
