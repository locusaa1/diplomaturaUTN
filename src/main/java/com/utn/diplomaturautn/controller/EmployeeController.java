package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.EmployeeDTO;
import com.utn.diplomaturautn.model.Employee;
import com.utn.diplomaturautn.model.User;
import com.utn.diplomaturautn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final PersonController personController;

    @Autowired
    public EmployeeController(EmployeeService employeeService, PersonController personController) {

        this.employeeService = employeeService;
        this.personController = personController;
    }

    public ResponseEntity<List<Employee>> response(List<Employee> employees) {

        return ResponseEntity.
                status(employees.isEmpty() ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(employees);
    }

    public ResponseEntity<Employee> response(Employee employee) {

        return ResponseEntity.
                status(employee == null ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(employee);
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAll() {

        return this.response(this.employeeService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getById(@RequestParam("id") int id) {

        return this.response(this.employeeService.getById(id));
    }

    /*@PostMapping("/")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO newEmployeeDTO) {

        return this.response(this.employeeService.
                addEmployee(Employee.builder().
                        person(this.personController.getById(newEmployeeDTO.getIdPerson()).getBody()).
                        user(User.builder().
                                username(newEmployeeDTO.getUsername()).
                                password(newEmployeeDTO.getPassword()).build()).build()));
    }*/
}
