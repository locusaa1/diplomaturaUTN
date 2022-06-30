package com.utn.diplomaturautn.serviceTest.callServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.InvalidDateException;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.Call;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByDateRangeTest extends CallServiceImplTest {

    @Test
    public void getByDateRangeTest_Client_Successful() {

        String from = "2022-04-20";
        Timestamp startTimeStamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2022-05-30";
        Timestamp endTimeStamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Client user = DataForModels.newClientIdUpdated();
        List<Call> callsFound = DataForModels.callList();

        given(this.callRepository.findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(startTimeStamp, endTimeStamp)).willReturn(Optional.of(callsFound));

        List<Call> invocationCallsFound = this.callServiceImpl.getByDateRange(from, to, user);

        assertThat(invocationCallsFound).isEqualTo(callsFound);

        verify(this.callRepository, times(1)).findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(startTimeStamp, endTimeStamp);
    }

    @Test
    public void getByDateRangeTest_Employee_Successful() {

        String from = "2022-04-15";
        Timestamp startTimeStamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2022-05-25";
        Timestamp endTimeStamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Employee user = DataForModels.newActiveEmployee();
        List<Call> callsFound = DataForModels.callList();

        given(this.callRepository.findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(startTimeStamp, endTimeStamp)).willReturn(Optional.of(callsFound));

        List<Call> invocationCallsFound = this.callServiceImpl.getByDateRange(from, to, user);

        assertThat(invocationCallsFound).isEqualTo(callsFound);

        verify(this.callRepository, times(1)).findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(startTimeStamp, endTimeStamp);
    }

    @Test
    public void getByDateRangeTest_FilterDates_InvalidDateException() {

        String from = "2022-07-20";
        Timestamp startTimestamp = Timestamp.valueOf(from.concat(" 23:00:00"));
        String to = LocalDate.now().toString();
        Timestamp endTimestamp = Timestamp.valueOf(to.concat(" " + LocalTime.now().toString()));
        Client user = DataForModels.newClientIdUpdated();

        Assertions.assertThrows(InvalidDateException.class, () -> this.callServiceImpl.getByDateRange(from, to, user));

        verify(this.callRepository, times(0)).findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(startTimestamp, endTimestamp);
    }

    @Test
    public void getByDateRangeTest_ParametersDateError_InvalidDateException() {

        String from = "2022-04-20";
        String to = "2022-03-20";
        Client user = DataForModels.newClientIdUpdated();

        Assertions.assertThrows(InvalidDateException.class, () -> this.callServiceImpl.getByDateRange(from, to, user));

        verify(this.callRepository, times(0)).findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(Timestamp.valueOf(from.concat(" 00:00:00")), Timestamp.valueOf(to.concat(" 23:59:59")));
    }

    @Test
    public void getByDateRangeTest_DateOutOfBounds_InvalidDateException() {

        String from = "2022-04-20";
        String to = "9999-04-04";
        Client user = DataForModels.newClientIdUpdated();

        Assertions.assertThrows(InvalidDateException.class, () -> this.callServiceImpl.getByDateRange(from, to, user));

        verify(this.callRepository, times(0)).findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(Timestamp.valueOf(from.concat(" 00:00:00")), Timestamp.valueOf(to.concat(" 23:59:59")));
    }

    @Test
    public void getByDateRangeTest_NoContentException() {
        String from = "2022-04-10";
        Timestamp startTimeStamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2022-05-20";
        Timestamp endTimeStamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Client user = DataForModels.newClientIdUpdated();

        given(this.callRepository.findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(startTimeStamp, endTimeStamp)).willReturn(Optional.empty());

        Assertions.assertThrows(NoContentException.class, () -> this.callServiceImpl.getByDateRange(from, to, user));

        verify(this.callRepository, times(1)).findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(startTimeStamp, endTimeStamp);
    }
}

