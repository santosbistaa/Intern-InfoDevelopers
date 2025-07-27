package com.datajpaexample.jpademo.service;

import com.datajpaexample.jpademo.entities.Employee;
import com.datajpaexample.jpademo.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository ;

//    @Autowired
//    public EmployeeService(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    // Create Operation
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    // Read All employee
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Read Employee by id
    public Employee getEmployeebById(int id){
        return employeeRepository.findById(id).orElse(null);
    }

    // Update Employee by id
    // Update name and email
    public Employee updateEmployee(int id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setEmail(updatedEmployee.getEmail());
            return employeeRepository.save(employee);
        }).orElse(null);
    }

    // Delete user by id
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
