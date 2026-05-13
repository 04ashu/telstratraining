package com.telstra.employee.service;

import com.telstra.employee.dto.EmployeeRequestDto;
import com.telstra.employee.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto dto);

    EmployeeResponseDto getEmployeeById(Long id);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto);

    void deleteEmployee(Long id);
}
