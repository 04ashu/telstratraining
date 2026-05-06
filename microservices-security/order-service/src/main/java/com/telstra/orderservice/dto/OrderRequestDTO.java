package com.telstra.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequestDTO {

    private Long userId;
    private Long productId;
}
