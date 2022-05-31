package com.utn.diplomaturautn.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.diplomaturautn.controller.ProvinceController;
import com.utn.diplomaturautn.model.Province;
import com.utn.diplomaturautn.service.ProvinceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ProvinceController.class)
//Allows us to let spring just scan for the beans from the specific controller. If we don't use this annotation, spring will scan all the controllers and mock all beans that controllers depends on.
public class ProvinceControllerTest {

    @Autowired
    //MockMvc fakes http requests making it possible to run controller tests without starting an entire http server
    private MockMvc mockMvc;

    @MockBean //Mock the service, so it does not use the service directly.
    private ProvinceService provinceService;

    @Test
    void getAllTest() throws Exception {

        //Setting the data to be used into the unit test
        Province firstProvince = new Province(1, "Buenos Aires", null);
        Province secondProvince = new Province(2, "Santa Fe", null);
        Province thirdProvince = new Province(3, "Río Negro", null);
        List<Province> provinceList = Arrays.asList(firstProvince, secondProvince, thirdProvince);

        //Writing the sequence of the test
        when(this.provinceService.getAll()).thenReturn(provinceList);

        //Performing the desired http request for the specific method
        this.mockMvc.perform(get("/api/province/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void getByIdTest() throws Exception {

        Province province = new Province(1, "Buenos Aires", null);

        when(this.provinceService.getById(1)).thenReturn(province);

        this.mockMvc.perform(get("/api/province/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Buenos Aires"))
                .andExpect(jsonPath("$.cities").doesNotExist());
    }

    @Test
    void addProvinceTest() throws Exception {

        Province province = new Province(0, "Buenos Aires", null);
        Province newProvince = new Province(1, "Buenos Aires", null);

        when(this.provinceService.addProvince(province)).thenReturn(newProvince);

        this.mockMvc.perform(post("/api/province/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(province)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Buenos Aires"))
                .andExpect(jsonPath("$.cities").doesNotExist());
    }

    @Test
    void addProvinceTestFailNullBody() throws Exception {

        this.mockMvc.perform(post("/api/province/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}
