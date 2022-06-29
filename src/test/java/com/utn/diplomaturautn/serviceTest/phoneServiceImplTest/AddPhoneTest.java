package com.utn.diplomaturautn.serviceTest.phoneServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.ErrorSavingEntityException;
import com.utn.diplomaturautn.exception.InvalidCallException;
import com.utn.diplomaturautn.exception.InvalidPhoneException;
import com.utn.diplomaturautn.model.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddPhoneTest extends PhoneServiceImplTest {

    @Test
    public void addPhoneTest_Successful() {

        String areaCode = "223";
        String phoneNumber = "2235012248";
        Phone save = DataForModels.savePhone();
        Phone savedPhone = DataForModels.newPhone();

        given(this.phoneRepository.save(save)).willReturn(savedPhone);

        Phone invocationPhoneSaved = this.phoneServiceImpl.addPhone(areaCode, phoneNumber);

        assertThat(invocationPhoneSaved).isEqualTo(savedPhone);

        verify(this.phoneRepository, times(1)).save(save);
    }

    @Test
    public void addPhoneTest_ErrorSavingEntityException() {

        String areaCode = "223";
        String phoneNumber = "2235012248";
        Phone save = DataForModels.savePhone();

        Exception nested = new SQLException("Nested cause:");

        Exception nestedEx = new SQLIntegrityConstraintViolationException("Duplicate entry.", nested);

        Exception exceptionThrown = new ErrorSavingEntityException("Invalid phone:", nestedEx);

        given(this.phoneRepository.save(save)).willThrow(exceptionThrown);

        Assertions.assertThrows(ErrorSavingEntityException.class, () -> this.phoneServiceImpl.addPhone(areaCode, phoneNumber));
    }

    @Test
    public void addPhoneTest_InvalidPhoneException() {

        String areaCode = "000";
        String phoneNumber = "2235012248";
        Phone save = DataForModels.savePhone();

        Assertions.assertThrows(InvalidPhoneException.class, () -> this.phoneServiceImpl.addPhone(areaCode, phoneNumber));

        verify(this.phoneRepository, times(0)).save(save);
    }
}
