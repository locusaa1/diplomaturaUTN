package com.utn.diplomaturautn.serviceTest.feeServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Fee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByIdTest extends FeeServiceImplTest {

    @Test
    public void getById_Successful() {

        Fee feeFound = DataForModels.newFee();

        given(this.feeRepository.findById(1)).willReturn(Optional.of(feeFound));

        Fee invocationFeeFound = this.feeServiceImpl.getById(1);

        assertThat(invocationFeeFound).isEqualTo(feeFound);

        verify(this.feeRepository, times(1)).findById(1);
    }

    @Test
    public void getById_InvalidBeanFieldsException() {

        Assertions.assertThrows(InvalidBeanFieldsException.class, () -> this.feeServiceImpl.getById(0));

        verify(this.feeRepository, times(0)).findById(0);
    }

    @Test
    public void getById_ResourceNotFoundException() {

        given(this.feeRepository.findById(2)).willReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.feeServiceImpl.getById(2));

        verify(this.feeRepository, times(1)).findById(2);
    }
}
