package com.utn.diplomaturautn.serviceTest.employeeServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetAllTest extends EmployeeServiceImplTest {

    @Test
    public void getAllTest_Successful() {

        List<Employee> employeeList = DataForModels.employeeList();

        given(this.employeeRepository.findAll()).willReturn(employeeList);

        List<Employee> invocationListFound = this.employeeServiceImpl.getAll();

        assertThat(invocationListFound).isNotEmpty();

        verify(this.employeeRepository, times(1)).findAll();
    }

    @Test
    public void getAllTest_NoContentException() {

        List<Employee> employeeEmptyList = DataForModels.employeeEmptyList();

        given(this.employeeRepository.findAll()).willReturn(employeeEmptyList);

        Assertions.assertThrows(NoContentException.class, () -> this.employeeServiceImpl.getAll());

        verify(this.employeeRepository, times(1)).findAll();
    }
}
