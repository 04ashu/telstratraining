package com.telstra.employee.repository;

import com.telstra.employee.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private  EmployeeRepository employeeRepository;

    @Test
    void shouldFindEmployeeByEmail(){

        Employee employee = Employee.builder()
                .name("Ashutosh")
                .email("ashu@gmail.com")
                .department("IT")
                .salary(50000.0)
                .build();

        employeeRepository.save(employee);

        Optional<Employee> result = employeeRepository.findByEmail("ashu@gmail.com");

        assertTrue(result.isPresent());
    }
}