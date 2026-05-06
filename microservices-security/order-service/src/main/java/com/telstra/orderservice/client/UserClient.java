package com.telstra.orderservice.client;

import com.telstra.orderservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserDTO getUser(@PathVariable Long id);
}
