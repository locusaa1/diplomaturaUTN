package com.utn.diplomaturautn.serviceTest.billServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Bill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByIdTest extends BillServiceImplTest {

    @Test
    public void getById_Successful() {

        Bill billFound = DataForModels.newBill();

        given(this.billRepository.findById(1)).willReturn(Optional.of(billFound));

        Bill invocationBillFound = this.billServiceImpl.getById(1);

        assertThat(invocationBillFound).isEqualTo(billFound);

        verify(this.billRepository, times(1)).findById(1);
    }

    @Test
    public void getById_InvalidBeanFieldsException() {

        Assertions.assertThrows(InvalidBeanFieldsException.class, () -> this.billServiceImpl.getById(0));

        verify(this.billRepository, times(0)).findById(0);
    }

    @Test
    public void getById_ResourceNotFoundException() {

        given(this.billRepository.findById(1)).willReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.billServiceImpl.getById(1));

        verify(this.billRepository, times(1)).findById(1);
    }
}
