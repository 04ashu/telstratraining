package com.telstra.userservice.service;

import com.telstra.userservice.dto.UserDTO;
import com.telstra.userservice.entity.User;
import com.telstra.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDTO getUserById(Long id){
        log.info("UserService: Fetching user with id={}", id);
        User user = repository.findById(id)
                .orElseThrow(()->{
                        log.warn("User not found with id={}", id);
                        return new RuntimeException("User not found");
                });

        log.info("UserService: User found with id={}", id);
        return new UserDTO(user.getId(),user.getName(),user.getEmail(), user.getRole());
    }

    public UserDTO createUser(UserDTO userdto){
        log.info("UserService: Creating user with email={}", userdto.getEmail());
        User user = new User();
        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());

        User savedUser = repository.save(user);

        log.info("UserService: User saved successfully with id={}", savedUser.getId());

        return new UserDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    public List<UserDTO> getAllUsers(){
        log.info("UserService: Fetching all users");
        List<User> users = repository.findAll();
        log.info("UserService: Fetched {} users from database", users.size());
        return users.stream()
                .map(user->new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                )).toList();
    }
}
