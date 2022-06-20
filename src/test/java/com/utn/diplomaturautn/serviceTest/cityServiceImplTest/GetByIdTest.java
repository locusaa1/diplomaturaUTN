package com.utn.diplomaturautn.serviceTest.cityServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetByIdTest extends CityServiceImplTest {

    @Test
    public void getById_Successful() {

        City cityFound = DataForModels.newCity();

        given(this.cityRepository.findById(1)).willReturn(Optional.of(cityFound));

        City invocationCityFound = this.cityServiceImpl.getById(1);

        assertThat(invocationCityFound).isEqualTo(cityFound);

        verify(this.cityRepository, times(1)).findById(1);
    }

    @Test
    public void getById_InvalidBeanFieldsException() {

        Assertions.assertThrows(InvalidBeanFieldsException.class, () -> this.cityServiceImpl.getById(0));

        verify(this.cityRepository, times(0)).findById(0);
    }

    @Test
    public void getById_ResourceNotFoundException() {

        given(this.cityRepository.findById(2)).willReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.cityServiceImpl.getById(2));

        verify(this.cityRepository, times(1)).findById(2);
    }
}
