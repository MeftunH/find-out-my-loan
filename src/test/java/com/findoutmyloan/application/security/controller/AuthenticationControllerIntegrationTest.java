package com.findoutmyloan.application.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.findoutmyloan.application.BaseIntegrationTest;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerIntegrationTest extends BaseIntegrationTest {
    private static final String BASE_URL="http://localhost:8082/auth";
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
    void login() throws Exception {
        String userToken=getCreatedAndAuthenticatedDummyUserToken(mockMvc);
        assertNotNull(userToken);
        deleteDummyUser(userToken, mockMvc);
    }

    @Test
    void register() throws Exception {
        CustomerSaveRequestDTO customerSaveRequestDTO=new CustomerSaveRequestDTO();
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

        MvcResult result=createDummyUser(customerSaveRequestDTO, mockMvc);
        boolean isSuccess=isRestResponseSuccess(result);
        assertTrue(isSuccess);
        customerRepository.deleteByIdentityNo(customerSaveRequestDTO.getIdentityNo());
    }
}