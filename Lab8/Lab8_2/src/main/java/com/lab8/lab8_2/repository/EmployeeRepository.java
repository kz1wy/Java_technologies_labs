package com.lab8.lab8_2.repository;

import com.lab8.lab8_2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
