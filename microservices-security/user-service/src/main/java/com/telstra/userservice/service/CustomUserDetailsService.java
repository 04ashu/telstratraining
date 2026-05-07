package com.telstra.userservice.service;

import com.telstra.userservice.entity.User;
import com.telstra.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("CustomUserDetailsService: DB email : email : " + email);
        User user = userRepository.findByEmail(email);

        System.out.println("CustomUserDetailsService: DB email : " + user.getEmail());

        if (user == null){
           throw  new UsernameNotFoundException("User Not Found with Email : " + email);
        }

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
