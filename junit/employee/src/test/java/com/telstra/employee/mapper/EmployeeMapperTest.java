package com.telstra.employee.mapper;

import com.telstra.employee.dto.EmployeeRequestDto;
import com.telstra.employee.dto.EmployeeResponseDto;
import com.telstra.employee.entity.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    @Test
    void shouldConvertDtoToEntity(){
        EmployeeRequestDto requestDto = EmployeeRequestDto.builder()
                .name("Ashutosh")
                .email("ashu@gmail.com")
                .department("IT")
                .salary(50000.0)
                .build();

        Employee employee = EmployeeMapper.toEntity(requestDto);
        assertEquals(requestDto.getName(),employee.getName());
    }

    @Test
    void shouldConvertEntityToResponseDto(){
        Employee employee = Employee.builder()
                .id(1L)
                .name("Ashutosh")
                .email("ashu@gmail.com")
                .department("IT")
                .salary(50000.0)
                .build();
        EmployeeResponseDto responseDto = EmployeeMapper.toResponseDto(employee);

        assertEquals(employee.getEmail(),responseDto.getEmail());
    }

}