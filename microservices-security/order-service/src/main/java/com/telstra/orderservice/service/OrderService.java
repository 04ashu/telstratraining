package com.telstra.orderservice.service;

import com.telstra.orderservice.client.ProductClient;
import com.telstra.orderservice.client.UserClient;
import com.telstra.orderservice.dto.OrderResponseDTO;
import com.telstra.orderservice.dto.ProductDTO;
import com.telstra.orderservice.dto.UserDTO;
import com.telstra.orderservice.entity.Order;
import com.telstra.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private WebClient webClient;

    @Autowired
    OrderRepository orderRepository;

    public OrderResponseDTO placeOrder(Long userId, Long productId){

        //Feign Calls to User and Product Service
        UserDTO user = userClient.getUser(userId);
        ProductDTO product = productClient.getProduct(productId);

        //Save Order
        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);

        orderRepository.save(order);

        return new OrderResponseDTO(
                order.getId(),
                user.getName(),
                product.getName(),
                product.getPrice()
        );
    }

    public List<OrderResponseDTO> getAllOrders(){
        return orderRepository.findAll()
                .stream()
                .map(order -> {

                    //Feign Call to User and Product Service
//                    UserDTO user = userClient.getUser(order.getUserId());
//                    ProductDTO product = productClient.getProduct(order.getProductId());

                    //Web Client call to user-service
                    UserDTO user = webClient
                            .get()
                            .uri("http://USER-SERVICE/users/{id}", order.getUserId())
                            .retrieve()
                            .bodyToMono(UserDTO.class)
                            .blockOptional()
                            .orElseThrow(()->new RuntimeException("User Not Found"));

                    //Web Client call to product-service
                    ProductDTO product = webClient
                            .get()
                            .uri("http://PRODUCT-SERVICE/products/{id}", order.getProductId())
                            .retrieve()
                            .bodyToMono(ProductDTO.class)
                            .blockOptional()
                            .orElseThrow(()-> new RuntimeException("Product Not Found"));

                    return new OrderResponseDTO(
                            order.getId(),
                            user.getName(),
                            product.getName(),
                            product.getPrice()
                    );
                }).toList();
    }
}
