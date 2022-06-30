package com.utn.diplomaturautn.serviceTest.billServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.InvalidDateException;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.Bill;
import com.utn.diplomaturautn.model.Call;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByDateRangeTest extends BillServiceImplTest {

    @Test
    public void getByDateRange_Client_Successful() {

        String from = "2020-01-15";
        Timestamp starTimestamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2020-01-29";
        Timestamp endTimestamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Client user = DataForModels.newActiveClient2();
        List<Bill> billsFound = DataForModels.billList();

        given(this.billRepository.findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(starTimestamp, endTimestamp)).willReturn(Optional.of(billsFound));

        List<Bill> invocationBillsFound = this.billServiceImpl.getByDateRange(from, to, user);

        assertThat(invocationBillsFound).isEqualTo(billsFound);

        verify(this.billRepository, times(1)).findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(starTimestamp, endTimestamp);
    }

    @Test
    public void getByDateRange_Employee_Successful() {

        String from = "2020-01-15";
        Timestamp starTimestamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2020-01-29";
        Timestamp endTimestamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Employee user = DataForModels.newEmployeeIdUpdated();
        List<Bill> billsFound = DataForModels.billList();

        given(this.billRepository.findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(starTimestamp, endTimestamp)).willReturn(Optional.of(billsFound));

        List<Bill> invocationBillsFound = this.billServiceImpl.getByDateRange(from, to, user);

        assertThat(invocationBillsFound).isEqualTo(billsFound);

        verify(this.billRepository, times(1)).findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(starTimestamp, endTimestamp);
    }

    @Test
    public void getByDateRangeTest_FilterDates_InvalidDateException() {

        String from = "2022-07-20";
        Timestamp startTimestamp = Timestamp.valueOf(from.concat(" 23:00:00"));
        String to = LocalDate.now().toString();
        Timestamp endTimestamp = Timestamp.valueOf(to.concat(" " + LocalTime.now().toString()));
        Client user = DataForModels.newClientIdUpdated();

        Assertions.assertThrows(InvalidDateException.class, () -> this.billServiceImpl.getByDateRange(from, to, user));

        verify(this.billRepository, times(0)).findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimestamp, endTimestamp);
    }

    @Test
    public void getByDateRangeTest_ParametersDateError_InvalidDateException() {

        String from = "2022-04-20";
        Timestamp startTimestamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2022-03-20";
        Timestamp endTimestamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Client user = DataForModels.newClientIdUpdated();

        Assertions.assertThrows(InvalidDateException.class, () -> this.billServiceImpl.getByDateRange(from, to, user));

        verify(this.billRepository, times(0)).findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimestamp, endTimestamp);
    }

    @Test
    public void getByDateRangeTest_DateOutOfBound_InvalidDateException() {

        String from = "2022-04-10";
        Timestamp startTimestamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "9999-04-04";
        Timestamp endTimestamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Client user = DataForModels.newClientIdUpdated();

        Assertions.assertThrows(InvalidDateException.class, () -> this.billServiceImpl.getByDateRange(from, to, user));

        verify(this.billRepository, times(0)).findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimestamp, endTimestamp);
    }

    @Test
    public void getByDateRangeTest_NoContentException() {

        String from = "2022-04-10";
        Timestamp startTimeStamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2022-05-20";
        Timestamp endTimeStamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Client user = DataForModels.newClientIdUpdated();

        given(this.billRepository.findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimeStamp, endTimeStamp)).willReturn(Optional.empty());

        Assertions.assertThrows(NoContentException.class, () -> this.billServiceImpl.getByDateRange(from, to, user));

        verify(this.billRepository, times(1)).findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimeStamp, endTimeStamp);
    }
}
