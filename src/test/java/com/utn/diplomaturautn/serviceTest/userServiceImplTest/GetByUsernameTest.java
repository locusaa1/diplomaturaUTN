package com.utn.diplomaturautn.serviceTest.userServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByUsernameTest extends UserServiceImplTest {

    @Test
    public void getByUsername_getsClient_Successful() {

        UserDetails clientFound = DataForModels.newActiveClient2();

        given(this.clientRepository.findByUsernameEquals("Star")).willReturn(Optional.of(clientFound));

        UserDetails invocationClientFound = this.userServiceImpl.getByUsername("Star");

        assertThat(invocationClientFound).isEqualTo(clientFound);

        verify(this.clientRepository, times(1)).findByUsernameEquals("Star");
    }

    @Test
    public void getByUsername_getsEmployee_Successful() {

        UserDetails employeeFound = DataForModels.newActiveEmployee2();

        given(this.clientRepository.findByUsernameEquals("Star")).willReturn(Optional.empty());
        given(this.employeeRepository.findByUsername("Star")).willReturn(Optional.of(employeeFound));

        UserDetails invocationEmployeeFound = this.userServiceImpl.getByUsername("Star");

        assertThat(invocationEmployeeFound).isEqualTo(employeeFound);

        verify(this.clientRepository, times(1)).findByUsernameEquals("Star");
        verify(this.employeeRepository, times(1)).findByUsername("Star");
    }

    @Test
    public void getByUsername_InvalidBeanFieldsException() {
        String username = "";

        Assertions.assertThrows(InvalidBeanFieldsException.class, () -> this.userServiceImpl.getByUsername(username));
    }

    @Test
    public void getByUsername_ResourceNotFoundException() {

        given(this.clientRepository.findByUsernameEquals("asd")).willReturn(Optional.empty());
        given(this.employeeRepository.findByUsername("asd")).willReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.userServiceImpl.getByUsername("asd"));

        verify(this.clientRepository, times(1)).findByUsernameEquals("asd");
        verify(this.employeeRepository, times(1)).findByUsername("asd");
    }
}
