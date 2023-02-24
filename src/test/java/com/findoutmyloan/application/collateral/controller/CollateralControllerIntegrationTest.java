package com.findoutmyloan.application.collateral.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.findoutmyloan.application.BaseIntegrationTest;
import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.enums.CollateralType;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class CollateralControllerIntegrationTest extends BaseIntegrationTest {
    private static final String BASE_URL="http://localhost:8082/api/v1/collateral";
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
    void saveCollateral() throws Exception {
        CollateralSaveRequestDTO collateralSaveRequestDTO=CollateralSaveRequestDTO.builder()
                .collateralType(CollateralType.MONEY)
                .worth(1000.0f)
                .build();

        String content=objectMapper.writeValueAsString(collateralSaveRequestDTO);
        String token=getCreatedAndAuthenticatedDummyUserToken(mockMvc);
        MvcResult result=mockMvc.perform(
                        post(BASE_URL)
                                .header("Authorization", token)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(isRestResponseSuccess(result));
        deleteDummyUser(token, mockMvc);
    }
}