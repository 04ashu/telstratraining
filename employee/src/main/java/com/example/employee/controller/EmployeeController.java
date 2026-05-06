package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    //Constructor Injection
    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    // Get All Employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        List<Employee> employees = service.getAllEmployees();

        if (employees.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }

    //Get Employee By Id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id){
        Employee employee = service.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    //Create Employee
    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee emp){
        Employee savedEmployee = service.saveEmployee(emp);
        return ResponseEntity.status(201).body(savedEmployee);
    }

    //Update Employee (Full Update)
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody Employee employee){
        Employee updatedEmployee = service.updateEmployee(id,employee);
        return ResponseEntity.ok(updatedEmployee);    //200
    }

    //Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();   //204
    }
}
