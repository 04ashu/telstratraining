package com.example.employee.service;

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

    public Employee saveEmployee(Employee emp){
        return repository.save(emp);
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
