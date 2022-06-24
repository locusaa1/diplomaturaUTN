package com.utn.diplomaturautn.serviceTest.callServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.model.Call;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.model.Employee;
import com.utn.diplomaturautn.model.Phone;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByDateRangeAndClientTest extends CallServiceImplTest {

    @Test
    public void getByDateRangeAndClientTest_Client_Successful() {

        String from = "2022-04-10";
        Timestamp startTimestamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2022-05-10";
        Timestamp endTimestamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Phone clientPhone = DataForModels.newPhone();
        Client client = DataForModels.newClientIdUpdated();
        List<Call> callsFound = DataForModels.callList();

        given(this.callRepository.findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(startTimestamp, endTimestamp)).willReturn(Optional.of(callsFound));

        List<Call> invocationCallsFound = this.callServiceImpl.getByDateRangeAndUser(from, to, clientPhone, client);

        assertThat(invocationCallsFound).isEqualTo(callsFound);

        verify(this.callRepository, times(1)).findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(startTimestamp, endTimestamp);
    }

    @Test
    public void getByDateRangeAndClientTest_Employee_Successful() {

        String from = "2022-04-10";
        Timestamp startTimestamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2022-05-10";
        Timestamp endTimestamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Phone clientPhone = DataForModels.newPhone();
        Employee client = DataForModels.newActiveEmployee();
        List<Call> callsFound = DataForModels.callList();

        given(this.callRepository.findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(startTimestamp, endTimestamp)).willReturn(Optional.of(callsFound));

        List<Call> invocationCallsFound = this.callServiceImpl.getByDateRangeAndUser(from, to, clientPhone, client);

        assertThat(invocationCallsFound).isEqualTo(callsFound);

        verify(this.callRepository, times(1)).findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(startTimestamp, endTimestamp);
    }
}
