package com.findoutmyloan.application.security.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.generic.dto.RestResponse;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerTest {
    private static final String BASE_URL="http://localhost:8082/auth";
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
        this.objectMapper=new ObjectMapper().registerModule(new JavaTimeModule());

    }
    @Test
    void login() {
    }

    @Test()
    void register() throws Exception {
        CustomerSaveRequestDTO customerSaveRequestDTO= new CustomerSaveRequestDTO();
            customerSaveRequestDTO.setName("Maftun");
            customerSaveRequestDTO.setSurname("Hashimli");
            customerSaveRequestDTO.setIdentityNo(89565435274L);
            customerSaveRequestDTO.setPassword("123Mm");
            customerSaveRequestDTO.setMonthlyIncome(6000f);
            customerSaveRequestDTO.setBirthDate(DateUtil.convertToDate(LocalDate.now().minusYears(20)));
            customerSaveRequestDTO.setPhoneNumber("5855555552");
            customerSaveRequestDTO.setPersonType(PersonType.CUSTOMER);
            customerSaveRequestDTO.setBaseAdditionalFieldsCreatedDate(DateUtil.convertToDate(LocalDate.now()));
            customerSaveRequestDTO.setBaseAdditionalFieldsUpdatedDate(DateUtil.convertToDate(LocalDate.now()));

        String jsonRequest = objectMapper.writeValueAsString(customerSaveRequestDTO);
        MvcResult result = mockMvc.perform(
                post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andReturn();
        RestResponse restResponse=objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);
        boolean isSuccess=restResponse.isSuccess();
        assertTrue(isSuccess);
    }
}