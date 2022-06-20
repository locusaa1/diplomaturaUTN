package com.utn.diplomaturautn.serviceTest.feeServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.Fee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetAllTest extends FeeServiceImplTest {

    @Test
    public void getAllTest_Successful() {

        List<Fee> feeList = DataForModels.feeList();

        given(this.feeRepository.findAll()).willReturn(feeList);

        List<Fee> invocationFeeList = this.feeServiceImpl.getAll();

        assertThat(invocationFeeList).isNotEmpty();

        verify(this.feeRepository, times(1)).findAll();
    }

    @Test
    public void getAllTest_NoContentException() {

        List<Fee> emptyList = new ArrayList<>();

        given(this.feeRepository.findAll()).willReturn(emptyList);

        Assertions.assertThrows(NoContentException.class, () -> this.feeServiceImpl.getAll());

        verify(this.feeRepository, times(1)).findAll();
    }
}
