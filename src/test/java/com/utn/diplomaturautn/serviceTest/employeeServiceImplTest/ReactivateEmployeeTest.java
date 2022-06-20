package com.utn.diplomaturautn.serviceTest.employeeServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.enumerated.EmployeeCondition;
import com.utn.diplomaturautn.exception.NothingToModifyException;
import com.utn.diplomaturautn.model.Employee;
import com.utn.diplomaturautn.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ReactivateEmployeeTest extends EmployeeServiceImplTest {

    @Test
    public void reactivateEmployee_Successful() {

        Employee discontinuedEmployee = DataForModels.newActiveEmployee2();
        discontinuedEmployee.setCondition(EmployeeCondition.INACTIVE);
        Employee reactivatedEmployee = DataForModels.newActiveEmployee2Copy();

        given(this.employeeRepository.findById(2)).willReturn(Optional.of((discontinuedEmployee)));
        given(this.employeeRepository.save(reactivatedEmployee)).willReturn(reactivatedEmployee);

        Employee invocationReactivatedEmployee = this.employeeServiceImpl.reactiveEmployee(2);

        assertThat(invocationReactivatedEmployee).isEqualTo(reactivatedEmployee);

        verify(this.employeeRepository, times(1)).findById(2);
        verify(this.employeeRepository, times(1)).save(reactivatedEmployee);
    }

    @Test
    public void reactivateClient_NothingToModifyException() {

        Employee employeeActive = DataForModels.newActiveEmployee2();

        given(this.employeeRepository.findById(2)).willReturn(Optional.of(employeeActive));

        Assertions.assertThrows(NothingToModifyException.class, () -> this.employeeServiceImpl.reactiveEmployee(2));

        verify(this.employeeRepository, times(1)).findById(2);
    }
}
