package com.stub.paymentservice.controller;

import com.example.paymentservice.client.model.OrderResponse;
import com.stub.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    public OrderResponse getPaymentDetails(@PathVariable Long id){
        return paymentService.fetchOrder(id);
    }
}
