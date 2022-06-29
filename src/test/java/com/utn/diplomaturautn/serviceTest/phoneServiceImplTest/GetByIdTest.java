package com.utn.diplomaturautn.serviceTest.phoneServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByIdTest extends PhoneServiceImplTest {

    @Test
    public void getByIdTest_Successful() {

        Phone phoneFound = DataForModels.newPhone();

        given(this.phoneRepository.findById(1)).willReturn(Optional.of(phoneFound));

        Phone invocationPhoneFound = this.phoneServiceImpl.getById(1);

        assertThat(invocationPhoneFound).isEqualTo(phoneFound);

        verify(this.phoneRepository, times(1)).findById(1);
    }

    @Test
    public void getById_InvalidBeanFieldsException() {

        Assertions.assertThrows(InvalidBeanFieldsException.class, () -> this.phoneServiceImpl.getById(0));

        verify(this.phoneRepository, times(0)).findById(0);
    }

    @Test
    public void getById_ResourceNotFoundException() {

        given(this.phoneRepository.findById(2)).willReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.phoneServiceImpl.getById(2));

        verify(this.phoneRepository, times(1)).findById(2);
    }
}
