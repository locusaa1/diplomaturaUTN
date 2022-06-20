package com.utn.diplomaturautn.serviceTest.clientServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.NothingToModifyException;
import com.utn.diplomaturautn.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ModifyClientTest extends ClientServiceImplTest {

    @Test
    public void modifyClient_Successful() {

        Optional<Client> clientOptionalPresent = Optional.of(DataForModels.newActiveClient2());
        Client clientFound = clientOptionalPresent.get();
        Client clientNewData = DataForModels.newClientIdUpdated();
        Client modifyClient = DataForModels.newActiveClient2Copy();

        Client finalFormClient = modifyClient.modifyUsingClient(clientNewData);

        given(this.clientRepository.findById(2)).willReturn(clientOptionalPresent);
        given(this.clientRepository.save(finalFormClient)).willReturn(finalFormClient);

        Client invocationClient = this.clientServiceImpl.modifyClient(clientNewData, 2);

        assertThat(invocationClient).isEqualTo(finalFormClient);

        verify(this.clientRepository, times(1)).findById(2);
        verify(this.clientRepository, times(1)).save(clientFound.modifyUsingClient(clientNewData));
    }

    @Test
    public void modifyClient_NothingToModifyException() {

        Optional<Client> clientOptionalPresent = Optional.of(DataForModels.newActiveClient2());
        Client clientNewData = DataForModels.newActiveClient2();

        given(this.clientRepository.findById(2)).willReturn(clientOptionalPresent);

        Assertions.assertThrows(NothingToModifyException.class, () -> this.clientServiceImpl.modifyClient(clientNewData, 2));

        verify(this.clientRepository, times(1)).findById(2);
    }
}
