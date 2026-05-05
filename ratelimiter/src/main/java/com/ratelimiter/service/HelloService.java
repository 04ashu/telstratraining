package com.ratelimiter.service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    //@RateLimiter(name = "myRateLimiter", fallbackMethod = "fallback")
    @RateLimiter(name = "myRateLimiter")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from Resilience4j Rate Limited API!");
    }

    public ResponseEntity<String> fallback(Throwable t){
        return ResponseEntity.status(429)
                .body("Too many requests. Rate Limit Exceeded.");
    }
}
