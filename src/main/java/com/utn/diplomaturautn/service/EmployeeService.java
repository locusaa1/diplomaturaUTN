package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.Employee;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService {

    /**
     * Lists all employees from the repository.
     *
     * @return a list of employees.
     */
    List<Employee> getAll();

    /**
     * Searches for the specific employee id into the repository.
     *
     * @param id the id of the specific employee.
     * @return the specific Employee object.
     */
    Employee getById(int id);

    /**
     * Adds a new employee into the repository.
     *
     * @param newEmployee the Employee object to be saved.
     * @return the Employee object with his last form from the repository.
     */
    Employee addEmployee(Employee newEmployee);

    Employee reactiveEmployee(int id);

    Employee deleteEmployee(int id);
}
