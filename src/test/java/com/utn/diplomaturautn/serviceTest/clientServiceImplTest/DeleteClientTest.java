package com.utn.diplomaturautn.serviceTest.clientServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.enumerated.ClientCondition;
import com.utn.diplomaturautn.exception.NothingToModifyException;
import com.utn.diplomaturautn.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteClientTest extends ClientServiceImplTest {

    @Test
    public void deleteClient_Successful() {

        Client activeClient = DataForModels.newActiveClient2();
        Client deletedClient = DataForModels.newActiveClient2Copy();
        deletedClient.setCondition(ClientCondition.INACTIVE);

        given(this.clientRepository.findById(2)).willReturn(Optional.of(activeClient));
        given(this.clientRepository.save(deletedClient)).willReturn(deletedClient);

        Client invocationDeletedClient = this.clientServiceImpl.deleteClient(2);

        assertThat(invocationDeletedClient).isEqualTo(deletedClient);

        verify(this.clientRepository, times(1)).findById(2);
        verify(this.clientRepository, times(1)).save(deletedClient);
    }

    @Test
    public void deleteClient_NothingToModifyException() {

        Client deletedClient = DataForModels.newActiveClient2Copy();
        deletedClient.setCondition(ClientCondition.INACTIVE);

        given(this.clientRepository.findById(2)).willReturn(Optional.of(deletedClient));

        Assertions.assertThrows(NothingToModifyException.class, () -> this.clientServiceImpl.deleteClient(2));

        verify(this.clientRepository, times(1)).findById(2);
    }
}
