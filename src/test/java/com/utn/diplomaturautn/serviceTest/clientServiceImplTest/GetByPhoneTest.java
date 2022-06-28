package com.utn.diplomaturautn.serviceTest.clientServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.model.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByPhoneTest extends ClientServiceImplTest {

    @Test
    public void getByPhone_Successful() {

        Phone clientPhone = DataForModels.newPhone();
        Client clientFound = DataForModels.newClientIdUpdated();

        given(this.clientRepository.findByPhoneEquals(clientPhone)).willReturn(Optional.of(clientFound));

        Client invocationClientFound = this.clientServiceImpl.getByPhone(clientPhone);

        assertThat(invocationClientFound).isEqualTo(clientFound);

        verify(this.clientRepository, times(1)).findByPhoneEquals(clientPhone);
    }

    @Test
    public void getByPhone_InvalidBeanFieldsException() {

        Phone clientPhoneEmpty = DataForModels.newPhone();
        clientPhoneEmpty.setNumber("");

        Assertions.assertThrows(InvalidBeanFieldsException.class, () -> this.clientServiceImpl.getByPhone(clientPhoneEmpty));

        verify(this.clientRepository, times(0)).findByPhoneEquals(clientPhoneEmpty);
    }

    @Test
    public void getByPhone_ResourceNotFoundException() {

        Phone clientPhone = DataForModels.newPhone();

        given(this.clientRepository.findByPhoneEquals(clientPhone)).willReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.clientServiceImpl.getByPhone(clientPhone));

        verify(this.clientRepository, times(1)).findByPhoneEquals(clientPhone);
    }
}
