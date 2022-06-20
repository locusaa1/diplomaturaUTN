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

public class DiscontinueClientTest extends ClientServiceImplTest {

    @Test
    public void discontinueClient_Successful() {

        Client activeClient = DataForModels.newActiveClient2();
        Client discontinuedClient = DataForModels.newActiveClient2Copy();
        discontinuedClient.setCondition(ClientCondition.DISCONTINUED);

        given(this.clientRepository.findById(2)).willReturn(Optional.of(activeClient));
        given(this.clientRepository.save(discontinuedClient)).willReturn(discontinuedClient);

        Client invocationClientDiscontinued = this.clientServiceImpl.discontinueClient(2);

        assertThat(invocationClientDiscontinued).isEqualTo(discontinuedClient);

        verify(this.clientRepository, times(1)).findById(2);
        verify(this.clientRepository, times(1)).save(discontinuedClient);
    }

    @Test
    public void discontinueClient_NothingToModifyException() {

        Client discontinueClient = DataForModels.newActiveClient2();
        discontinueClient.setCondition(ClientCondition.DISCONTINUED);

        given(this.clientRepository.findById(2)).willReturn(Optional.of(discontinueClient));

        Assertions.assertThrows(NothingToModifyException.class, () -> this.clientServiceImpl.discontinueClient(2));

        verify(this.clientRepository, times(1)).findById(2);
    }
}
