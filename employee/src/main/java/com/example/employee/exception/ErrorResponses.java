package com.example.employee.exception;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorResponses {

    private final LocalDateTime timestamp;
    private final int status;
    private final String message;
    private final List<FieldErrorResponse> errors;

    public ErrorResponses(LocalDateTime timestamp, int status, String message, List<FieldErrorResponse> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

}
