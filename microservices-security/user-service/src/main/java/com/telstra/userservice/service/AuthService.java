package com.telstra.userservice.service;

import com.telstra.userservice.dto.AuthResponse;
import com.telstra.userservice.dto.LoginRequest;
import com.telstra.userservice.dto.SignupRequest;
import com.telstra.userservice.entity.User;
import com.telstra.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
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

        try{

            if(userRepository.existsByEmail(request.getEmail())){
                throw new RuntimeException("User Already exists with email: " + request.getEmail());
            }

            User user = new User();

            user.setName(request.getName());
            user.setEmail(request.getEmail());

            user.setPassword(passwordEncoder.encode(request.getPassword()));

            userRepository.save(user);

            return "User Registered Successfully";
        } catch (Exception ex){
            throw new RuntimeException("Signup failed: " + ex.getMessage());
        }
    }

    public AuthResponse signin(LoginRequest request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        String token = jwtService.generateToken(request.getEmail());

        return new AuthResponse(token);
    }
}
