package com.utn.diplomaturautn.serviceTest.employeeServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.enumerated.EmployeeCondition;
import com.utn.diplomaturautn.exception.NothingToModifyException;
import com.utn.diplomaturautn.model.Employee;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteEmployeeTest extends EmployeeServiceImplTest {

    @Test
    public void deleteEmployee_Successful() {

        Employee activeEmployee = DataForModels.newActiveEmployee2();
        Employee deletedEmployee = DataForModels.newActiveEmployee2Copy();
        deletedEmployee.setCondition(EmployeeCondition.INACTIVE);

        given(this.employeeRepository.findById(2)).willReturn(Optional.of(activeEmployee));
        given(this.employeeRepository.save(deletedEmployee)).willReturn(deletedEmployee);

        Employee invocationDeletedEmployee = this.employeeServiceImpl.deleteEmployee(2);

        assertThat(invocationDeletedEmployee).isEqualTo(deletedEmployee);

        verify(this.employeeRepository, times(1)).findById(2);
        verify(this.employeeRepository, times(1)).save(deletedEmployee);
    }

    @Test
    public void deleteEmployee_NothingToModifyException() {

        Employee deletedEmployee = DataForModels.newActiveEmployee2();
        deletedEmployee.setCondition(EmployeeCondition.INACTIVE);

        given(this.employeeRepository.findById(2)).willReturn(Optional.of(deletedEmployee));

        Assertions.assertThrows(NothingToModifyException.class, () -> this.employeeServiceImpl.deleteEmployee(2));

        verify(this.employeeRepository, times(1)).findById(2);
    }
}
