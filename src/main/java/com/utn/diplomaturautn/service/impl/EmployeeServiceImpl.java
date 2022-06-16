package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.exception.*;
import com.utn.diplomaturautn.model.Employee;
import com.utn.diplomaturautn.repositroy.EmployeeRepository;
import com.utn.diplomaturautn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.utn.diplomaturautn.enumerated.EmployeeCondition.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {

        List<Employee> employeeList = this.employeeRepository.findAll();

        if (employeeList.isEmpty()) {

            throw new NoContentException("There is no content in the database from this entity.");
        } else {

            return employeeList;
        }
    }

    @Override
    public Employee getById(int employeeId) {

        if (employeeId <= 0) {

            throw new InvalidBeanFieldsException("The id must be higher than 0.");
        } else {

            Optional<Employee> employee = this.employeeRepository.findById(employeeId);

            if (employee.isEmpty()) {

                throw new ResourceNotFoundException("There is not a register with the specific id.");
            } else {

                return employee.get();
            }
        }
    }

    @Override
    public Employee addEmployee(Employee newEmployee) {

        try {

            return this.employeeRepository.save(newEmployee);
        } catch (Exception exception) {

            throw new ErrorSavingEntityException("Saving the entity failed. Nested exception message: " + exception.getMessage());
        }
    }

    @Override
    public Employee modifyEmployee(Employee employeeNewData, int employeeId) {

        Employee employeeFound = this.getById(employeeId);

        if (!employeeFound.equals(employeeNewData)) {

            return this.employeeRepository.save(employeeFound.modifyUsingEmployee(employeeNewData));
        } else {
            throw new NothingToModifyException("The new data matches all the employee from the database, there is nothing to modify.");
        }
    }

    @Override
    public Employee reactiveEmployee(int employeeId) {

        Employee employeeFound = this.getById(employeeId);

        if (employeeFound.getCondition() == ACTIVE) {

            throw new NothingToModifyException("The employee is already active.");
        } else {

            employeeFound.setCondition(ACTIVE);
        }
        return this.employeeRepository.save(employeeFound);
    }

    @Override
    public Employee deleteEmployee(int employeeId) {

        Employee employeeFound = this.getById(employeeId);

        if (employeeFound.getCondition() == INACTIVE) {

            throw new NothingToModifyException("The employee is already inactive.");
        } else {

            employeeFound.setCondition(INACTIVE);
        }
        return employeeRepository.save(employeeFound);
    }
}
