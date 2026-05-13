package com.telstra.employee.service.impl;

import com.telstra.employee.dto.EmployeeRequestDto;
import com.telstra.employee.dto.EmployeeResponseDto;
import com.telstra.employee.entity.Employee;
import com.telstra.employee.exception.DuplicateEmployeeException;
import com.telstra.employee.exception.EmployeeNotFoundException;
import com.telstra.employee.mapper.EmployeeMapper;
import com.telstra.employee.repository.EmployeeRepository;
import com.telstra.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {
        employeeRepository.findByEmail(dto.getEmail())
                .ifPresent(employee -> {
                    throw new DuplicateEmployeeException(
                            "Employee already exits with email: " + dto.getEmail()
                    );
                });
        Employee employee = EmployeeMapper.toEntity(dto);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toResponseDto(savedEmployee);
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->
                        new EmployeeNotFoundException(
                                "Employee not found with id: "+ id
                        ));
        return EmployeeMapper.toResponseDto(employee);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream().map(EmployeeMapper::toResponseDto)
                .toList();
    }

    @Override
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->
                        new EmployeeNotFoundException("Employee not found with id: " + id));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toResponseDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->
                        new EmployeeNotFoundException("Employee not found with id: "+id));

        employeeRepository.delete(employee);
    }
}
