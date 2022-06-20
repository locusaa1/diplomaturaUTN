package com.utn.diplomaturautn.serviceTest.employeeServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.ErrorSavingEntityException;
import com.utn.diplomaturautn.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddEmployeeTest extends EmployeeServiceImplTest {

    @Test
    public void addEmployee_Successful() {

        Employee addEmployee = DataForModels.newActiveEmployee();
        Employee savedEmployee = DataForModels.newEmployeeIdUpdated();

        given(this.employeeRepository.save(addEmployee)).willReturn(savedEmployee);

        Employee invocationClientSave = this.employeeServiceImpl.addEmployee(addEmployee);

        assertThat(invocationClientSave).isEqualTo(savedEmployee);

        verify(this.employeeRepository, times(1)).save(addEmployee);
    }

    @Test
    public void addEmployee_ErrorSavingEntityException() {

        Employee addEmployee = DataForModels.newActiveEmployee();

        given(this.employeeRepository.save(addEmployee)).willThrow(ErrorSavingEntityException.class);

        Assertions.assertThrows(ErrorSavingEntityException.class, () -> this.employeeServiceImpl.addEmployee(addEmployee));

        verify(this.employeeRepository, times(1)).save(addEmployee);
    }
}
