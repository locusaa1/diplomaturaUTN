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

public class ReactivateClientTest extends ClientServiceImplTest {

    @Test
    public void reactivateClient_Successful() {

        Client discontinuedClient = DataForModels.newActiveClient2();
        discontinuedClient.setCondition(ClientCondition.DISCONTINUED);
        Client reactivatedClient = DataForModels.newActiveClient2Copy();

        given(this.clientRepository.findById(2)).willReturn(Optional.of(discontinuedClient));
        given(this.clientRepository.save(reactivatedClient)).willReturn(reactivatedClient);

        Client invocationReactivatedClient = this.clientServiceImpl.reactivateClient(2);

        assertThat(invocationReactivatedClient).isEqualTo(reactivatedClient);

        verify(this.clientRepository, times(1)).findById(2);
        verify(this.clientRepository, times(1)).save(reactivatedClient);
    }

    @Test
    public void reactivateClient_NothingToModifyException() {

        Client activeClient = DataForModels.newActiveClient2();

        given(this.clientRepository.findById(2)).willReturn(Optional.of(activeClient));

        Assertions.assertThrows(NothingToModifyException.class, () -> this.clientServiceImpl.reactivateClient(2));

        verify(this.clientRepository, times(1)).findById(2);
    }
}
