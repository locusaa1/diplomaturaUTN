package com.utn.diplomaturautn.serviceTest.clientServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.ErrorSavingEntityException;
import com.utn.diplomaturautn.exception.InvalidCallException;
import com.utn.diplomaturautn.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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

    @Test
    public void addClient_ErrorSavingEntityException() {

        Client addClient = DataForModels.newActiveClient2();

        Exception nested = new SQLException("Nested cause:");

        Exception nestedEx = new SQLIntegrityConstraintViolationException("Duplicate entry.", nested);

        Exception exceptionThrown = new ErrorSavingEntityException("Invalid client: ", nestedEx);

        given(this.clientRepository.save(addClient)).willThrow(exceptionThrown);

        Assertions.assertThrows(ErrorSavingEntityException.class, () -> this.clientServiceImpl.addClient(addClient));

        verify(this.clientRepository, times(1)).save(addClient);
    }
}
