package com.telstra.userservice.service;

import com.telstra.userservice.dto.UserDTO;
import com.telstra.userservice.entity.User;
import com.telstra.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDTO getUserById(Long id){
        User user = repository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found"));

        return new UserDTO(user.getId(),user.getName(),user.getEmail(), user.getRole());
    }

    public UserDTO createUser(UserDTO userdto){
        User user = new User();
        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());

        User savedUser = repository.save(user);

        return new UserDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    public List<UserDTO> getAllUsers(){
        return repository.findAll().stream()
                .map(user->new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                )).toList();
    }
}
