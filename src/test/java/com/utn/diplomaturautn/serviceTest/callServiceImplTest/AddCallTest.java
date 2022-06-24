package com.utn.diplomaturautn.serviceTest.callServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.enumerated.ClientCondition;
import com.utn.diplomaturautn.exception.InvalidCallException;
import com.utn.diplomaturautn.model.Call;
import com.utn.diplomaturautn.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddCallTest extends CallServiceImplTest {

    @Test
    public void addCallTest_Successful() {

        Call addCall = DataForModels.newCall();
        Call savedCall = DataForModels.newCallUpdatedId();
        Client originClient = DataForModels.newActiveClient();

        given(this.callRepository.save(addCall)).willReturn(savedCall);

        Call invocationCallSaved = this.callServiceImpl.addCall(addCall, originClient);

        assertThat(invocationCallSaved).isEqualTo(savedCall);

        verify(this.callRepository, times(1)).save(addCall);
    }

    @Test
    public void addCallTest_DuplicateEntry_InvalidCallException() {

        Call addCall = DataForModels.newCall();
        Client originClient = DataForModels.newActiveClient();

        Exception nested = new SQLException("Nested cause:");

        Exception nestedEx = new SQLIntegrityConstraintViolationException("Duplicate entry.", nested);

        Exception exceptionThrown = new InvalidCallException("Invalid call:", nestedEx);

        given(this.callRepository.save(addCall)).willThrow(exceptionThrown);

        Assertions.assertThrows(InvalidCallException.class, () -> this.callServiceImpl.addCall(addCall, originClient));

        verify(this.callRepository, times(1)).save(addCall);
    }

    @Test
    public void addCallTest_InactiveClient_InvalidCallException() {

        Call addCall = DataForModels.newCall();
        Client originInactiveClient = DataForModels.newActiveClient();
        originInactiveClient.setCondition(ClientCondition.INACTIVE);

        Assertions.assertThrows(InvalidCallException.class, () -> this.callServiceImpl.addCall(addCall, originInactiveClient));

        verify(this.callRepository, times(0)).save(addCall);
    }

    @Test
    public void addCallTest_DateOutOfBounds_InvalidCallException() {

        Call addCall = DataForModels.newCall();
        addCall.setStartDate(Timestamp.valueOf("9999-02-01 20:00:00"));
        Client originClient = DataForModels.newActiveClient();

        Assertions.assertThrows(InvalidCallException.class, () -> this.callServiceImpl.addCall(addCall, originClient));

        verify(this.callRepository, times(0)).save(addCall);
    }

    @Test
    public void addCallTest_CallingOwnNumber_InvalidCallException() {

        Call addCall = DataForModels.newCall();
        addCall.setDestinationPhone(DataForModels.newPhone());
        Client originClient = DataForModels.newActiveClient();

        Assertions.assertThrows(InvalidCallException.class, () -> this.callServiceImpl.addCall(addCall, originClient));

        verify(this.callRepository, times(0)).save(addCall);
    }
}
