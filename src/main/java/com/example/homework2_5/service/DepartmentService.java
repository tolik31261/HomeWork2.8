package com.example.homework2_5.service;

import com.example.homework2_5.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployeeWithMaxSalary(Integer department) {
        return employeeService.getAll().stream()
                .filter(e->e.getDepartment()==department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }
    public Employee getEmployeeWithMinSalary(Integer department) {
        return employeeService.getAll().stream()
                .filter(e->e.getDepartment()==department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }
}
