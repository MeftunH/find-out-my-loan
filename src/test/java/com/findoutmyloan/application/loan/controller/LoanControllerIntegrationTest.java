package com.findoutmyloan.application.loan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.findoutmyloan.application.BaseIntegrationTest;
import com.findoutmyloan.application.collateral.enums.CollateralType;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.enums.LoanResult;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
class LoanControllerIntegrationTest extends BaseIntegrationTest {
    private static final String BASE_URL="http://localhost:8082/api/v1/loan";
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
    void saveLoan() throws Exception {
        LoanSaveRequestDTO loanSaveRequestDTO=LoanSaveRequestDTO.builder()
                .paybackGuaranteeType(PaybackGuaranteeType.COLLATERAL)
                .amount(1000)
                .result(LoanResult.APPROVED)
                .build();
        String content=objectMapper.writeValueAsString(loanSaveRequestDTO);
        String token=getCreatedAndAuthenticatedDummyUserToken(mockMvc);
        MvcResult result=mockMvc.perform(
                        post(BASE_URL)
                                .header("Authorization", token)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(isRestResponseSuccess(result));
        deleteDummyUser(token, mockMvc);
    }

    @Test
    void applyLoan() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO=LoanApplicationRequestDTO.builder()
                .paybackGuaranteeType(PaybackGuaranteeType.COLLATERAL)
                .collateralType(CollateralType.MONEY)
                .collateralWorth(1000)
                .paybackGuaranteeType(PaybackGuaranteeType.COLLATERAL)
                .build();

        String content=objectMapper.writeValueAsString(loanApplicationRequestDTO);
        String token=getCreatedAndAuthenticatedDummyUserToken(mockMvc);
        MvcResult result=mockMvc.perform(
                        post(BASE_URL+"/apply")
                                .header("Authorization", token)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(isRestResponseSuccess(result));
        deleteDummyUser(token, mockMvc);
    }
}