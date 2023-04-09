package com.lab8.lab8_2.service;

import com.lab8.lab8_2.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(int id);

    void saveEmployee(Employee employee);

    void deleteEmployeeById(int id);

}