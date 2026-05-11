package com.telstra.userservice.controller;

import com.telstra.userservice.dto.AuthResponse;
import com.telstra.userservice.dto.LoginRequest;
import com.telstra.userservice.dto.SignupRequest;
import com.telstra.userservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request){
        log.info("POST /auth/signup called for email={}", request.getEmail());
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest request){
        log.info("POST /auth/signin called for email={}", request.getEmail());
        return ResponseEntity.ok(authService.signin(request));
    }
}
