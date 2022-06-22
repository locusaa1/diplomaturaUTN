package com.utn.diplomaturautn.serviceTest.clientServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.ErrorSavingEntityException;
import com.utn.diplomaturautn.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddClientTest extends ClientServiceImplTest {

    @DisplayName("Test for addClient() method returning saved Client")
    @Test
    public void addClient_Successful() {

        Client addClient = DataForModels.newActiveClient();
        Client savedClient = DataForModels.newClientIdUpdated();

        given(this.clientRepository.save(addClient)).willReturn(savedClient);

        Client saved = this.clientServiceImpl.addClient(addClient);

        Assertions.assertEquals(saved, savedClient);

        verify(this.clientRepository, times(1)).save(addClient);
    }

    /*@Test
    public void addClient_ErrorSavingEntityException() {

        Client addClient = DataForModels.newActiveClient2();

        given(this.clientRepository.save(addClient)).willThrow(ErrorSavingEntityException.class);

        Assertions.assertThrows(ErrorSavingEntityException.class, () -> this.clientServiceImpl.addClient(addClient));

        verify(this.clientRepository, times(1)).save(addClient);
    }*/
}
