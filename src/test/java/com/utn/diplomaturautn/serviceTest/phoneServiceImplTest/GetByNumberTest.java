package com.utn.diplomaturautn.serviceTest.phoneServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByNumberTest extends PhoneServiceImplTest {

    @Test
    public void getByNumberTest_Successful() {

        String phoneNumber = "2235012248";
        Phone phoneFound = DataForModels.newPhone();

        given(this.phoneRepository.findPhoneByNumber(phoneNumber)).willReturn(Optional.of(phoneFound));

        Phone invocationPhoneFound = this.phoneServiceImpl.getByNumber(phoneNumber);

        assertThat(invocationPhoneFound).isEqualTo(phoneFound);

        verify(this.phoneRepository, times(1)).findPhoneByNumber(phoneNumber);
    }

    @Test
    public void getByNumberTest_ResourceNotFoundException() {

        String phoneNumber = "2235012248";

        given(this.phoneRepository.findPhoneByNumber(phoneNumber)).willReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.phoneServiceImpl.getByNumber(phoneNumber));

        verify(this.phoneRepository, times(1)).findPhoneByNumber(phoneNumber);
    }
}
