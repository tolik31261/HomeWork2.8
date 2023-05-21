package com.example.homework2_5.service;

import com.example.homework2_5.exeption.EmployeeAlreadyAddedException;
import com.example.homework2_5.exeption.EmployeeNotFoundException;
import com.example.homework2_5.exeption.EmployeeStorageIsFullException;
import com.example.homework2_5.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class EmployeeService {

    private static final int SIZE_LIMIT = 5;
    final private Collection<Employee> employees = new ArrayList<>(SIZE_LIMIT);

    public Employee add(Employee employee) {
        if (employees.size() >= SIZE_LIMIT) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = find (firstName, lastName);
        employees.remove(employee);
        return employee;
    }

    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees);
    }

}
