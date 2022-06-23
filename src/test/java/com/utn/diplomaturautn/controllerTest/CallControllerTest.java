/*package com.utn.diplomaturautn.controllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.diplomaturautn.controller.CallController;
import com.utn.diplomaturautn.data.DataForModels;
import com.utn.diplomaturautn.enumerated.UserType;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.model.Phone;
import com.utn.diplomaturautn.repositroy.CallRepository;
import com.utn.diplomaturautn.repositroy.PhoneRepository;
import com.utn.diplomaturautn.service.CallService;
import com.utn.diplomaturautn.service.ClientService;
import com.utn.diplomaturautn.service.PhoneService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CallControllerTest {

    @InjectMocks
    private CallController callControllerTest;

    @Autowired
    private MockMvc mvc;

    @Mock
    private CallService callService;

    @Mock
    private PhoneService phoneService;

    @Mock
    private ClientService clientService;


    @Before
    public void createControllerWithMocks() {

        MockitoAnnotations.openMocks(this);
        MockMvcBuilders.standaloneSetup(callControllerTest).build();
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    public void getAll() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/call/"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    public void getById() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/call/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ANTENNA")
    public void addCall() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/api/call/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(DataForModels.newCallDTO())))
                .andExpect(status().isCreated());
    }
}
*/