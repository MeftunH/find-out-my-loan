package com.findoutmyloan.application.surety.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.findoutmyloan.application.BaseIntegrationTest;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.generic.dto.RestResponse;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.enums.SuretyType;
import com.findoutmyloan.application.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SuretyControllerIntegrationTest extends BaseIntegrationTest {
    private static final String BASE_URL="http://localhost:8082/api/v1/surety";
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        this.mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
        this.objectMapper=new ObjectMapper().registerModule(new JavaTimeModule());
    }
    @Test
    void saveSurety() throws Exception {
        SuretySaveRequestDTO suretySaveRequestDTO=SuretySaveRequestDTO.builder()
                .name("Maftun")
                .surname("Hashimli")
                .suretyType(SuretyType.ORDINARY)
                .identityNo(72429036182L)
                .birthDate(DateUtil.convertToDate(LocalDate.now()))
                .phoneNumber("52025559")
                .personType(PersonType.SURETY)
                .build();

        String content=objectMapper.writeValueAsString(suretySaveRequestDTO);
        String token=getCreatedAndAuthenticatedDummyUserToken(mockMvc);
        MvcResult result=mockMvc.perform(
                        post(BASE_URL)
                                .header("Authorization", token)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(isRestResponseSuccess(result));
        customerRepository.deleteByIdentityNo(suretySaveRequestDTO.getIdentityNo());
        deleteDummyUser(token,mockMvc);
    }
}