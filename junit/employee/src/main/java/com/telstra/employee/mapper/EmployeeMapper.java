package com.telstra.employee.mapper;

import com.telstra.employee.dto.EmployeeRequestDto;
import com.telstra.employee.dto.EmployeeResponseDto;
import com.telstra.employee.entity.Employee;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequestDto dto){
        return Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .department(dto.getDepartment())
                .salary(dto.getSalary())
                .build();
    }

    public static EmployeeResponseDto toResponseDto(Employee employee){
        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .build();
    }
}
