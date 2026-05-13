package com.telstra.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telstra.employee.dto.EmployeeResponseDto;
import com.telstra.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    void shouldReturnAllEmployees() throws Exception{

        EmployeeResponseDto employeeResponseDto =
                EmployeeResponseDto.builder()
                        .id(1L)
                        .name("Ashutosh")
                        .email("ashu@gmail.com")
                        .department("IT")
                        .salary(50000.0)
                        .build();

        when(employeeService.getAllEmployees()).thenReturn(List.of(employeeResponseDto));

        mockMvc.perform(get("/employees")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name")
                        .value("Ashutosh"));
    }
}