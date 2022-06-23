package com.utn.diplomaturautn.serviceTest.callServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.Call;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetAllTest extends CallServiceImplTest {

    @Test
    public void getAllTest_Successful() {

        List<Call> calls = DataForModels.callList();

        given(this.callRepository.findAll()).willReturn(calls);

        List<Call> callsFound = this.callServiceImpl.getAll();

        assertThat(callsFound).isEqualTo(calls);

        verify(this.callRepository, times(1)).findAll();
    }

    @Test
    public void getAllTest_NoContentException() {

        List<Call> calls = new ArrayList<>();

        given(this.callRepository.findAll()).willReturn(calls);

        Assertions.assertThrows(NoContentException.class, () -> this.callServiceImpl.getAll());

        verify(this.callRepository, times(1)).findAll();
    }
}
