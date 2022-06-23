package com.utn.diplomaturautn.serviceTest.callServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Call;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByIdTest extends CallServiceImplTest {

    @Test
    public void getById_Successful() {

        Call callFound = DataForModels.newCallUpdatedId();

        given(this.callRepository.findById(1)).willReturn(Optional.of(callFound));

        Call invocationCallFound = this.callServiceImpl.getById(1);

        assertThat(invocationCallFound).isEqualTo(callFound);

        verify(this.callRepository, times(1)).findById(1);
    }

    @Test
    public void getById_InvalidBeanFieldsException() {

        Assertions.assertThrows(InvalidBeanFieldsException.class, () -> this.callServiceImpl.getById(0));

        verify(this.callRepository, times(0)).findById(0);
    }

    @Test
    public void getById_ResourceNotFoundException() {

        given(this.callRepository.findById(2)).willReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.callServiceImpl.getById(2));

        verify(this.callRepository, times(1)).findById(2);
    }
}
