package com.telstra.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telstra.employee.dto.EmployeeRequestDto;
import com.telstra.employee.dto.EmployeeResponseDto;
import com.telstra.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateEmployee() throws Exception {
        EmployeeResponseDto responseDto=
                EmployeeResponseDto.builder()
                        .id(1L)
                        .name("Ashutosh")
                        .email("ashu@gmail.com")
                        .department("IT")
                        .salary(50000.0)
                        .build();

        EmployeeRequestDto requestDto = EmployeeRequestDto.builder()
                .name("Ashutosh")
                .email("ashu@gmail.com")
                .department("IT")
                .salary(50000.0)
                .build();

        when(employeeService.createEmployee(any())).thenReturn(responseDto);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ashutosh"));
    }

    @Test
    void shouldGetEmployeeById() throws Exception {
        EmployeeResponseDto responseDto=
                EmployeeResponseDto.builder()
                        .id(1L)
                        .name("Ashutosh")
                        .email("ashu@gmail.com")
                        .department("IT")
                        .salary(50000.0)
                        .build();
        when(employeeService.getEmployeeById(1L)).thenReturn(responseDto);
        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ashutosh"));
    }

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
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name")
                        .value("Ashutosh"));
    }

    @Test
    void shouldUpdateEmployee() throws Exception{
        EmployeeResponseDto responseDto=
                EmployeeResponseDto.builder()
                        .id(1L)
                        .name("Updated")
                        .email("updated@gmail.com")
                        .department("HR")
                        .salary(70000.0)
                        .build();

        EmployeeRequestDto requestDto = EmployeeRequestDto.builder()
                .name("Updated")
                .email("updated@gmail.com")
                .department("HR")
                .salary(70000.0)
                .build();

        when(employeeService.updateEmployee(any(Long.class),any())).thenReturn(responseDto);

        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.department").value("HR"));

    }

    @Test
    void shouldDeleteEmployee() throws Exception{

        doNothing().when(employeeService).deleteEmployee(1L);
        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isNoContent());
    }
}