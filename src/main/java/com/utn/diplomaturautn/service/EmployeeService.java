package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.Employee;

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
     * @param employeeId the id of the specific employee.
     * @return the specific Employee object.
     */
    Employee getById(int employeeId);

    /**
     * Adds a new employee into the repository.
     *
     * @param newEmployee the Employee object to be saved.
     * @return the Employee object with his last form from the repository.
     */
    Employee addEmployee(Employee newEmployee);

    /**
     * Modifies the fields that do not match the employee into the repository.
     *
     * @param employeeNewData the Employee object with the new data.
     * @param employeeId      the id from the employee to be modified into the repository.
     * @return the Employee object with his new form from the repository.
     */
    Employee modifyEmployee(Employee employeeNewData, int employeeId);

    /**
     * Sets the specific employee condition (from employeeId) to active again.
     *
     * @param employeeId the employee id to be activated.
     * @return the employee info.
     */
    Employee reactiveEmployee(int employeeId);

    /**
     * Sets the specific employee condition (from employeeId) to "inactive".
     *
     * @param employeeId the employee id to be inactive.
     * @return the emplyee info.
     */
    Employee deleteEmployee(int employeeId);
}
