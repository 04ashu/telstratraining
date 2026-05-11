package com.telstra.userservice.controller;

import com.telstra.userservice.dto.UserDTO;
import com.telstra.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        log.info("UserController: GET /users/{} called", id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        log.info("UserController: POST /users called to create user with {}", userDTO);
        UserDTO savedUser = userService.createUser(userDTO);
        log.info("UserController: User created successfully with id={}", savedUser.getId());
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        log.info("UserController: GET /users called");
        List<UserDTO> users = userService.getAllUsers();

        if(users.isEmpty()){
            log.info("No users found");
            return ResponseEntity.noContent().build();
        }
        log.info("UserController: Returning {} users", users.size());
        return ResponseEntity.ok(users);
    }
}
