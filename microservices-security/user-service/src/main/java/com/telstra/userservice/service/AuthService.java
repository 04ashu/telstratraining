package com.telstra.userservice.service;

import com.telstra.userservice.dto.AuthResponse;
import com.telstra.userservice.dto.LoginRequest;
import com.telstra.userservice.dto.SignupRequest;
import com.telstra.userservice.entity.User;
import com.telstra.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public String signup(SignupRequest request){

        log.info("Signup attempt for email={}", request.getEmail());

        try{

            if(userRepository.existsByEmail(request.getEmail())){
                log.warn("Signup failed: user already exists with email={}", request.getEmail());

                throw new RuntimeException("User Already exists with email: " + request.getEmail());
            }

            User user = new User();

            user.setName(request.getName());
            user.setEmail(request.getEmail());

            user.setPassword(passwordEncoder.encode(request.getPassword()));

            userRepository.save(user);

            log.info("User registered successfully, email={}", user.getEmail());

            return "User Registered Successfully";
        } catch (Exception ex){
            log.error("Unexpected error during signup for email={}", request.getEmail(), ex);
            throw new RuntimeException("Signup failed: " + ex.getMessage());
        }
    }

    public AuthResponse signin(LoginRequest request){
        log.info("Signin attempt for email={}", request.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        String token = jwtService.generateToken(request.getEmail());

        log.info("Signin successful for email={}", request.getEmail());

        return new AuthResponse(token);
    }
}
