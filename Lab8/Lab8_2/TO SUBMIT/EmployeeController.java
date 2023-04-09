package com.lab8.lab8_2.controller;

import com.lab8.lab8_2.model.Employee;
import com.lab8.lab8_2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "index";
    }

    @GetMapping("/employees/add")
    public String addEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add";
    }

    @PostMapping("/employees/add")
    public String addEmployeeSubmit(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable int id, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "index";
        } else {
            return "redirect:/employees";
        }
    }

    @PostMapping("/employees/edit/{id}")
    public String editEmployeeSubmit(@PathVariable int id, @ModelAttribute("employee") Employee employee) {
        Optional<Employee> oldEmployee = employeeService.getEmployeeById(id);
        if (oldEmployee.isPresent()) {
            employee.setId(id);
            employeeService.saveEmployee(employee);
        }
        return "redirect:/employees";
    }

    @PostMapping("/employees/delete/{id}")
    public String deleteEmployeeSubmit(@PathVariable int id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }

}
