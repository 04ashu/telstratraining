package com.telstra.userservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@Slf4j
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private  String jwtSecret;

    @Value("${application.security.jwt.expiration}")
    private int jwtExpirationMs;

    //Secret Key Initialization
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    //Generate Jwt Token
    public String generateToken(String username){

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key)
                .compact();
    }

    // Validate JWT Token
    public boolean validateToken(String token, String expectedUsername) {
        try {
            String username = extractUsername(token);
            System.out.println("Username equals expected username : " + username.equals(expectedUsername));
            return username.equals(expectedUsername) && !isTokenExpired(token);
        } catch (Exception e) {
            log.error("JWT validation error: {}", e.getMessage());
            return false;
        }
    }

    //Parse & Validate Signature
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //Extract Username
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    //Check token Expiry
    public boolean isTokenExpired(String token) {
        Date expiration = parseClaims(token).getExpiration();
        System.out.println("isToken Expired : " + expiration.before(new Date()));
        return expiration.before(new Date());
    }

}
