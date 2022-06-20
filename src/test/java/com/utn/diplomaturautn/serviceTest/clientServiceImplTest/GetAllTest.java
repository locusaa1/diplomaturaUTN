package com.utn.diplomaturautn.serviceTest.clientServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetAllTest extends ClientServiceImplTest{

    @DisplayName("Test for getAll() method returning a list with content")
    @Test
    public void getAllTest_Successful() {

        List<Client> clientList = DataForModels.clientList();

        given(this.clientRepository.findAll()).willReturn(clientList);

        List<Client> invocationListFound = this.clientServiceImpl.getAll();

        assertThat(invocationListFound).isNotEmpty();

        verify(this.clientRepository, times(1)).findAll();
    }

    @DisplayName("Test for getAll() method returning empty list")
    @Test
    public void getAllTest_Throws_NoContentException() {

        List<Client> emptyList = new ArrayList<>();

        given(this.clientRepository.findAll()).willReturn(emptyList);

        Assertions.assertThrows(NoContentException.class, () -> this.clientServiceImpl.getAll());

        verify(this.clientRepository, times(1)).findAll();
    }
}
