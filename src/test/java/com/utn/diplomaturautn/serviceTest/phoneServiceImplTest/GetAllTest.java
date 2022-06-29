package com.utn.diplomaturautn.serviceTest.phoneServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetAllTest extends PhoneServiceImplTest {

    @Test
    public void getAllTest_Successful() {

        List<Phone> phones = DataForModels.phoneList();

        given(this.phoneRepository.findAll()).willReturn(phones);

        List<Phone> invocationPhones = this.phoneServiceImpl.getAll();

        assertThat(invocationPhones).isEqualTo(phones);

        verify(this.phoneRepository, times(1)).findAll();
    }

    @Test
    public void getAllTest_NoContentException() {

        List<Phone> emptyList = new ArrayList<>();

        given(this.phoneRepository.findAll()).willReturn(emptyList);

        Assertions.assertThrows(NoContentException.class, () -> this.phoneServiceImpl.getAll());

        verify(this.phoneRepository, times(1)).findAll();
    }
}
