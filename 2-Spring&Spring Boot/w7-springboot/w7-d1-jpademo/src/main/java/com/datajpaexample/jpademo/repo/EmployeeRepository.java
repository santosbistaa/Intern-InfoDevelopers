package com.datajpaexample.jpademo.repo;

import com.datajpaexample.jpademo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// This will be used as interface for the CRUD operations that we will perform
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
