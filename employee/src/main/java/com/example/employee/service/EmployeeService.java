package com.example.employee.service;

import com.example.employee.exception.BadRequestException;
import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

//    private final EmployeeRepository repository;

    public EmployeeService(){
        System.out.println("Constructor : Employee Service");
    }
    @Value("${app.message}")
    private String message;

    //Constructor Injection
//    public EmployeeService(EmployeeRepository repository){
//        this.repository = repository;
//    }

    //Field Injection
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees(){
        System.out.println("Message from Config : " + message);
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException(id));
    }

    public Employee saveEmployee(Employee emp){
        validationRole(emp.getRole());
        return repository.save(emp);
    }

    public Employee updateEmployee(Long id, Employee emp){
        Employee existing = repository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException(id));
        validationRole(emp.getRole());
        existing.setName(emp.getName());
        existing.setRole(emp.getRole());

        return repository.save(existing);
    }

    public void deleteEmployee(Long id){
        if(!repository.existsById(id)){
            throw new EmployeeNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private void validationRole(String role){
        if(role == null) return;

        if(!role.equalsIgnoreCase("ADMIN") &&
            !role.equalsIgnoreCase("USER")){
            throw new BadRequestException("Role must be ADMIN or USER");
        }
    }

    @PostConstruct
    public void init(){
        System.out.println("Bean Initialized : EmployeeService");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Bean Destroyed : EmployeeService");
    }
}
