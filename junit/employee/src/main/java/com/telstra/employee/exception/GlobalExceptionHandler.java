package com.telstra.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFound(EmployeeNotFoundException ex){
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message",ex.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEmployeeException.class)
    public ResponseEntity<Object> handleDuplicateEmployee(DuplicateEmployeeException ex){
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message",ex.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error-> errors.put(
                error.getField(),error.getDefaultMessage()
        ));

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
