package com.findoutmyloan.application.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.findoutmyloan.application.generic.dto.RestResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerControllerTest {

    private static final String BASE_URL="http://localhost:8080/api/v1/customer";
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
    void getMyAccountInformation() {
    }

    @Test
    void deleteAccount() {
    }

    @Test
    void updateAccount() {
    }

    @Test
    void findLoansByCustomerIdentityNoAndCustomerBirthDate() throws Exception {
        MvcResult result=mockMvc.perform(get(BASE_URL+"/45586784456/1999-01-01/find-loans")
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        RestResponse restResponse=objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);
        boolean isSuccess=restResponse.isSuccess();
        assertTrue(isSuccess);
    }
}