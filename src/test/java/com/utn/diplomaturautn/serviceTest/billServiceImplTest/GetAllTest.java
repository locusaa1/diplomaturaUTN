package com.utn.diplomaturautn.serviceTest.billServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.Bill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetAllTest extends BillServiceImplTest {

    @Test
    public void getAllTest_Successful() {

        List<Bill> billList = DataForModels.billList();

        given(this.billRepository.findAll()).willReturn(billList);

        List<Bill> invocationBillList = this.billServiceImpl.getAll();

        assertThat(invocationBillList).isNotEmpty();

        verify(this.billRepository, times(1)).findAll();
    }

    @Test
    public void getAllTest_NoContentException() {

        List<Bill> emptyList = new ArrayList<>();

        given(this.billRepository.findAll()).willReturn(emptyList);

        Assertions.assertThrows(NoContentException.class, () -> this.billServiceImpl.getAll());

        verify(this.billRepository, times(1)).findAll();
    }
}
