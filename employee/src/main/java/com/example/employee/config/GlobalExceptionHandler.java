package com.example.employee.config;

import com.example.employee.exception.BadRequestException;
import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.exception.ErrorResponses;
import com.example.employee.exception.FieldErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Validation Errors through Map
/*    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex){

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(),error.getDefaultMessage()));

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("Status",400);
        response.put("errors",fieldErrors);

        return response;
    }

 */
    //Validation Errors through Error DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponses handleValidationExceptions(MethodArgumentNotValidException ex){

        List<FieldErrorResponse> fieldErrors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(error->new FieldErrorResponse(
                        error.getField(),
                        error.getDefaultMessage()
                )).collect(Collectors.toList());

        return new ErrorResponses(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                fieldErrors
        );
    }

    //Employee Not Found
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponses handleEmployeeNotFound(EmployeeNotFoundException ex){
        return new ErrorResponses(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
    }

    //Generic Bad Request (business Logic)
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponses handleBadRequest(BadRequestException ex){
        return new ErrorResponses(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    //Catch-all Exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponses handleGenericException(Exception ex){
        return new ErrorResponses(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Something Went Wrong",
                null
        );
    }
}
