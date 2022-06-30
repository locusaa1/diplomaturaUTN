package com.utn.diplomaturautn.serviceTest.billServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.InvalidClientRequest;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByDateRangeAndClientTest extends BillServiceImplTest {

    @Test
    public void getByDateRangeAndClientTest_Client_Successful() {

        String from = "2022-04-10";
        Timestamp startTimestamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2022-05-10";
        Timestamp endTimestamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Client client = DataForModels.newActiveClient2();
        List<Bill> billsFound = DataForModels.billList();

        given(this.billRepository.findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimestamp, endTimestamp)).willReturn(Optional.of(billsFound));

        List<Bill> invocationBillsFound = this.billServiceImpl.getByDateRangeAndClient(from, to, client, client);

        assertThat(invocationBillsFound).isEqualTo(billsFound);

        verify(this.billRepository, times(1)).findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimestamp, endTimestamp);
    }

    @Test
    public void getByDateRangeAndClientTest_Employee_Successful() {

        String from = "2022-04-10";
        Timestamp startTimestamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2022-05-10";
        Timestamp endTimestamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Client client = DataForModels.newActiveClient2();
        Employee employee = DataForModels.newActiveEmployee2();
        List<Bill> billsFound = DataForModels.billList();

        given(this.billRepository.findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimestamp, endTimestamp)).willReturn(Optional.of(billsFound));

        List<Bill> invocationBillsFound = this.billServiceImpl.getByDateRangeAndClient(from, to, client, employee);

        assertThat(invocationBillsFound).isEqualTo(billsFound);

        verify(this.billRepository, times(1)).findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimestamp, endTimestamp);
    }

    @Test
    public void getByDateRangeAndClientTest_FilterListDate_NoContentException() {

        String from = "2022-04-05";
        Timestamp startTimeStamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2022-05-04";
        Timestamp endTimeStamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Client user = DataForModels.newClientIdUpdated();
        List<Bill> billsFound = DataForModels.billList();

        given(this.billRepository.findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimeStamp, endTimeStamp)).willReturn(Optional.of(billsFound));

        Assertions.assertThrows(NoContentException.class, () -> this.billServiceImpl.getByDateRangeAndClient(from, to, user, user));

        verify(this.billRepository, times(1)).findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimeStamp, endTimeStamp);
    }

    @Test
    public void getByDateRangeAndClientTest_InvalidClientRequestException() {

        String from = "2022-04-05";
        Timestamp startTimeStamp = Timestamp.valueOf(from.concat(" 00:00:00"));
        String to = "2022-05-04";
        Timestamp endTimeStamp = Timestamp.valueOf(to.concat(" 23:59:59"));
        Client user = DataForModels.newClientIdUpdated();
        Client userRequesting = DataForModels.newActiveClient2();
        List<Bill> billsFound = DataForModels.billList();

        given(this.billRepository.findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimeStamp, endTimeStamp)).willReturn(Optional.of(billsFound));

        Assertions.assertThrows(InvalidClientRequest.class, () -> this.billServiceImpl.getByDateRangeAndClient(from, to, user, userRequesting));

        verify(this.billRepository, times(1)).findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(startTimeStamp, endTimeStamp);
    }
}
