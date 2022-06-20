package com.utn.diplomaturautn.serviceTest.cityServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetAllTest extends CityServiceImplTest {

    @Test
    public void getAllTest_Successful() {

        List<City> cityList = DataForModels.cityList();

        given(this.cityRepository.findAll()).willReturn(cityList);

        List<City> invocationCityList = this.cityServiceImpl.getAll();

        assertThat(invocationCityList).isNotEmpty();

        verify(this.cityRepository, times(1)).findAll();
    }

    @Test
    public void getAllTest_NoContentException() {

        List<City> emptyList = new ArrayList<>();

        given(this.cityRepository.findAll()).willReturn(emptyList);

        Assertions.assertThrows(NoContentException.class, () -> this.cityServiceImpl.getAll());

        verify(this.cityRepository, times(1)).findAll();
    }
}
