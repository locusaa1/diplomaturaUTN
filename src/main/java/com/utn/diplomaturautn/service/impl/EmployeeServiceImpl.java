package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.model.Employee;
import com.utn.diplomaturautn.repositroy.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements com.utn.diplomaturautn.service.EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public void EmployeeRepository(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {

        return this.employeeRepository.findAll();
    }

    public Employee getById(int id) {

        return this.employeeRepository.findById(id).get();
    }

    public Employee addEmployee(Employee newEmployee) {

        return this.employeeRepository.save(newEmployee);
    }
}
