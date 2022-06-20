package com.utn.diplomaturautn.serviceTest.employeeServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.NothingToModifyException;
import com.utn.diplomaturautn.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ModifyEmployeeTest extends EmployeeServiceImplTest {

    @Test
    public void modifyEmployee_Successful() {

        Optional<Employee> employeeOptionalPresent = Optional.of(DataForModels.newActiveEmployee2());
        Employee employeeFound = employeeOptionalPresent.get();
        Employee employeeNewData = DataForModels.newEmployeeIdUpdated();
        Employee modifyEmployee = DataForModels.newActiveEmployee2Copy();

        Employee finalFormEmployee = modifyEmployee.modifyUsingEmployee(employeeNewData);

        given(this.employeeRepository.findById(2)).willReturn(employeeOptionalPresent);
        given(this.employeeRepository.save(finalFormEmployee)).willReturn(finalFormEmployee);

        Employee invocationEmployee = this.employeeServiceImpl.modifyEmployee(employeeNewData, 2);

        assertThat(invocationEmployee).isEqualTo(employeeFound);

        verify(this.employeeRepository, times(1)).findById(2);
        verify(this.employeeRepository, times(1)).save(finalFormEmployee);
    }

    @Test
    public void modifyEmployee_NothingToModify() {

        Optional<Employee> employeeOptionalPresent = Optional.of(DataForModels.newActiveEmployee2());
        Employee employeeNewData = DataForModels.newActiveEmployee2Copy();

        given(this.employeeRepository.findById(2)).willReturn(employeeOptionalPresent);

        Assertions.assertThrows(NothingToModifyException.class, () -> this.employeeServiceImpl.modifyEmployee(employeeNewData, 2));

        verify(this.employeeRepository, times(1)).findById(2);
    }
}
