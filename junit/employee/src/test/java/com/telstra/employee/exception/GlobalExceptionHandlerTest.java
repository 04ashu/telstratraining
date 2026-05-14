package com.telstra.employee.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    @Test
    void shouldHandleEmployeeNotFoundException(){

        EmployeeNotFoundException exception = new EmployeeNotFoundException("Not Found");
        ResponseEntity<Object> response = handler.handleEmployeeNotFound(exception);
        assertEquals(404,response.getStatusCode().value());
    }

    @Test
    void shouldHandleDuplicateEmployeeException(){
        DuplicateEmployeeException ex = new DuplicateEmployeeException("Duplicate");
        ResponseEntity<Object> response = handler.handleDuplicateEmployee(ex);
        assertEquals(400,response.getStatusCode().value());
    }

    @Test
    void shouldHandleValidationException(){

        //Mock exception
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);

        //Mock binding result
        BindingResult bindingResult = mock(BindingResult.class);

        //Create a field error
        FieldError fieldError = new FieldError("employee", "email", "Invalid email");

        //Stub behavior
        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        //Call handler
        ResponseEntity<Object> response = handler.handleValidationException(ex);

        //Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Map<String, String> body = (Map<String, String>) response.getBody();
        assertEquals("Invalid email", body.get("email"));

    }

}