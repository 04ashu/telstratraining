package com.telstra.employee.service.impl;

import com.telstra.employee.dto.EmployeeRequestDto;
import com.telstra.employee.dto.EmployeeResponseDto;
import com.telstra.employee.entity.Employee;
import com.telstra.employee.exception.DuplicateEmployeeException;
import com.telstra.employee.exception.EmployeeNotFoundException;
import com.telstra.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeRequestDto requestDto;

    @BeforeEach
    void setup(){
        employee = Employee.builder()
                .id(1L)
                .name("Ashutosh")
                .email("ashu@gmail.com")
                .department("IT")
                .salary(50000.0)
                .build();

        requestDto = EmployeeRequestDto.builder()
                .name("Ashutosh")
                .email("ashu@gmail.com")
                .department("IT")
                .salary(50000.0)
                .build();

    }

    @Test
    void shouldCreateEmployeeSuccessfully(){
        when(employeeRepository.findByEmail(requestDto.getEmail())).thenReturn(Optional.empty());
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponseDto responseDto = employeeService.createEmployee(requestDto);
        assertNotNull(responseDto);
        assertEquals("Ashutosh",responseDto.getName());

        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void shouldThrowDuplicateEmployeeException(){
        when(employeeRepository.findByEmail(requestDto.getEmail())).thenReturn(Optional.of(employee));
        assertThrows(DuplicateEmployeeException.class,
                ()-> employeeService.createEmployee(requestDto));
    }

    @Test
    void shouldGetEmployeeByIdSuccessfully(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        EmployeeResponseDto responseDto = employeeService.getEmployeeById(1L);
        assertEquals(employee.getName(),responseDto.getName());
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class,
                ()-> employeeService.getEmployeeById(1L));
    }

    @Test
    void shouldReturnAllEmployees(){
        when(employeeRepository.findAll()).thenReturn(List.of(employee));
        List<EmployeeResponseDto> employees = employeeService.getAllEmployees();
        assertEquals(1,employees.size());
    }

    @Test
    void shouldUpdateEmployeeSuccessfully(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponseDto response = employeeService.updateEmployee(1L,requestDto);
        assertEquals("Ashutosh", response.getName());
    }

    @Test
    void shouldThrowExceptionWhileUpdatingEmployee(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class,
                ()->employeeService.updateEmployee(1L,requestDto));
    }

    @Test
    void shouldDeleteEmployeeSuccessfully(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(employee);
        employeeService.deleteEmployee(1L);
        verify(employeeRepository).delete(employee);
    }

    @Test
    void shouldThrowExceptionWhileDeletingEmployee(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class,
                ()->employeeService.deleteEmployee(1L));
    }
}