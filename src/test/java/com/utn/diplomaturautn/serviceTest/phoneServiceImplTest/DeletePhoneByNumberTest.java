package com.utn.diplomaturautn.serviceTest.phoneServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.ErrorSavingEntityException;
import com.utn.diplomaturautn.exception.InvalidPhoneException;
import com.utn.diplomaturautn.model.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class DeletePhoneByNumberTest extends PhoneServiceImplTest {

    @Test
    public void deletePhoneByNumberTest_Successful() {

        String phoneNumber = "2235012248";

        Assertions.assertDoesNotThrow(() -> this.phoneServiceImpl.deletePhoneByNumber(phoneNumber));

        verify(this.phoneRepository, times(1)).deletePhoneByNumber(phoneNumber);
    }

    @Test
    public void deletePhoneByNumberTest_InvalidPhoneException() {

        String phoneNumber = "2235012248";

        Exception nested = new SQLException("Nested cause:");

        Exception nestedEx = new SQLIntegrityConstraintViolationException("Duplicate entry.", nested);

        Exception exceptionThrown = new InvalidPhoneException("Invalid phone:", nestedEx);

        doThrow(exceptionThrown).when(this.phoneRepository).deletePhoneByNumber(isA(String.class));

        Assertions.assertThrows(InvalidPhoneException.class, () -> this.phoneServiceImpl.deletePhoneByNumber(phoneNumber));

        verify(this.phoneRepository, times(1)).deletePhoneByNumber(phoneNumber);
    }
}
