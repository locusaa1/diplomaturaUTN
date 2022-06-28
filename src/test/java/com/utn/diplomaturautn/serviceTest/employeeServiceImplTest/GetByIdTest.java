package com.utn.diplomaturautn.serviceTest.employeeServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByIdTest extends EmployeeServiceImplTest {

    @Test
    public void getById_Successful() {

        Optional<Employee> optionalEmployeePresent = Optional.of(DataForModels.newActiveEmployee2());

        Employee employeeFound = optionalEmployeePresent.get();

        given(this.employeeRepository.findById(2)).willReturn(optionalEmployeePresent);

        Employee invocationEmployeeFound = this.employeeServiceImpl.getById(2);

        assertThat(invocationEmployeeFound).isEqualTo(employeeFound);

        verify(this.employeeRepository, times(1)).findById(2);
    }

    @Test
    public void getById_InvalidBeanFieldException() {

        Assertions.assertThrows(InvalidBeanFieldsException.class, () -> this.employeeServiceImpl.getById(0));

        verify(this.employeeRepository, times(0)).findById(0);
    }

    @Test
    public void getById_ResourceNotFoundException() {

        Optional<Employee> optionalEmployeeEmpty = Optional.empty();

        given(this.employeeRepository.findById(2)).willReturn(optionalEmployeeEmpty);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.employeeServiceImpl.getById(2));

        verify(this.employeeRepository, times(1)).findById(2);
    }
}
