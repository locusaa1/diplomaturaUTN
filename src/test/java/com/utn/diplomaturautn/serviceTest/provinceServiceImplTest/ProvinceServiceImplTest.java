package com.utn.diplomaturautn.serviceTest.provinceServiceImplTest;

import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Province;
import com.utn.diplomaturautn.repositroy.ProvinceRepository;
import com.utn.diplomaturautn.service.impl.ProvinceServiceImpl;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProvinceServiceImplTest {

    @Mock
    private ProvinceRepository provinceRepository;

    @InjectMocks
    private ProvinceServiceImpl provinceServiceImpl;

    @DisplayName("JUnit test for getAll() method, returns content in the list")
    @Test
    public void getAllTest() {

        Province firstProvince = new Province(1, "Buenos Aires", null);
        Province secondProvince = new Province(2, "Santa Fe", null);
        Province thirdProvince = new Province(3, "Río Negro", null);
        List<Province> provinceList = Arrays.asList(firstProvince, secondProvince, thirdProvince);

        //Preparing the context
        given(this.provinceRepository.findAll()).willReturn(provinceList);

        //Setting the action
        when(this.provinceServiceImpl.getAll()).thenReturn(provinceList);

        //Implementing the tested method
        List<Province> provincesListResponse = this.provinceServiceImpl.getAll();

        //Asserting results
        assertThat(provincesListResponse).isNotEmpty();

        verify(this.provinceRepository).findAll();
    }

    @DisplayName("Junit test for getAll() method, throws no content exception")
    @Test
    public void getAllTestThrowsNoContentException() {

        List<Province> provinceList = new ArrayList<>();

        given(this.provinceRepository.findAll()).willReturn(provinceList);

        Assertions.assertThrows(NoContentException.class, () -> this.provinceServiceImpl.getAll());

        verify(this.provinceRepository).findAll();
    }

    @DisplayName("JUnit test for the getById(int id) method, returns a province")
    @Test
    public void getByIdTest() {

        Province province = new Province(1, "Buenos Aires", null);

        Optional<Province> provinceOptionalPresent = Optional.of(province);

        given(this.provinceRepository.findById(1)).willReturn(provinceOptionalPresent);

        Province savedProvince = this.provinceServiceImpl.getById(1);

        assertThat(savedProvince).isEqualTo(province);

        verify(this.provinceRepository).findById(1);
    }

    @DisplayName("JUnit test for the getById(int id) method, throws invalidBeanFieldsException")
    @Test
    public void getByIdTestThrowsInvalidBeanFieldsException() {

        Assertions.assertThrows(InvalidBeanFieldsException.class, () -> this.provinceServiceImpl.getById(0));
    }

    @DisplayName("JUnit test for the getById(int id) method, throws ResourceNotFoundException")
    @Test
    public void getByIdTestThrowsResourceNotFoundException() {


        when(this.provinceRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Province> province = this.provinceRepository.findById(1);

        assertThat(province).isEmpty();

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.provinceServiceImpl.getById(1));
    }

    @DisplayName("JUnit test for the addProvince(Province newProvince) method, returns the saved Province")
    @Test
    public void addProvinceTest() {

        Province newProvince = new Province(0, "Buenos Aires", null);
        Province savedProvince = new Province(1, "Buenos Aires", null);

        when(this.provinceRepository.save(newProvince)).thenReturn(savedProvince);

        Province responseProvince = this.provinceServiceImpl.addProvince(newProvince);

        assertThat(responseProvince).isEqualTo(savedProvince);
    }

    @DisplayName("JUnit test for the method addProvince(Province newProvince), throws exception")
    @Test
    public void addProvinceTestThrowsException() {
        /*Province newProvince = new Province(1, "Buenos Aires", null);

        when(this.provinceServiceImpl.addProvince(newProvince)).thenThrow(DataIntegrityViolationException.class);

        Assertions.assertThrows(DataIntegrityViolationException.class,()->this.provinceServiceImpl.addProvince(newProvince));*/

        Province newProvince = new Province(1, "Buenos Aires", null);

        given(this.provinceRepository.save(newProvince)).willThrow(ConstraintViolationException.class);
        //when(this.provinceServiceImpl.addProvince(newProvince)).thenThrow(DataIntegrityViolationException.class);

        Assertions.assertThrows(ConstraintViolationException.class, () -> this.provinceServiceImpl.addProvince(newProvince));

        verify(this.provinceRepository).save(any(Province.class));
    }
}
