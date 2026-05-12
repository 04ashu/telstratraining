package com.stub.paymentservice.service;

import com.example.paymentservice.client.api.OrderControllerApi;
import com.example.paymentservice.client.model.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final OrderControllerApi orderApi;

    public PaymentService() {
        this.orderApi = new OrderControllerApi();

        this.orderApi
                .getApiClient()
                .setBasePath("http://localhost:8080");
    }

    public OrderResponse fetchOrder(Long id){
        return orderApi.getOrder(id);
    }
}
