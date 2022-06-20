package com.utn.diplomaturautn.serviceTest.clientServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class GetByIdTest extends ClientServiceImplTest {

    @DisplayName("Test for getById() method returning the specific Client")
    @Test
    public void getById_Successful() {

        Optional<Client> clientOptionalPresent = Optional.of(DataForModels.newActiveClient2());

        Client clientFound = clientOptionalPresent.get();

        given(this.clientRepository.findById(2)).willReturn(clientOptionalPresent);

        Client invocationClientFound = this.clientServiceImpl.getById(2);

        assertThat(invocationClientFound).isEqualTo(clientFound);

        verify(this.clientRepository, times(1)).findById(2);
    }

    @Test
    public void getById_InvalidBeanFieldsException() {

        Assertions.assertThrows(InvalidBeanFieldsException.class, () -> this.clientServiceImpl.getById(0));

        verify(this.clientRepository, times(0)).findById(0);
    }

    @Test
    public void getById_ResourceNotFoundException() {

        Optional<Client> clientOptionalEmpty = Optional.empty();

        given(this.clientRepository.findById(3)).willReturn(clientOptionalEmpty);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.clientServiceImpl.getById(3));

        verify(this.clientRepository, times(1)).findById(3);
    }
}
