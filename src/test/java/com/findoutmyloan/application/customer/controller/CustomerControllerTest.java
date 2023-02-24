package com.findoutmyloan.application.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.findoutmyloan.application.BaseTest;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.generic.dto.RestResponse;
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

import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerControllerTest extends BaseTest {

    private static final String BASE_URL="http://localhost:8082/api/v1/customer";
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
    void getMyAccountInformation() throws Exception {
        String token=getCreatedAndAuthenticatedDummyUserToken(mockMvc);
        MvcResult result=mockMvc.perform(
                        get(BASE_URL)
                                .header("Authorization", token)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        RestResponse restResponse=objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);
        assertTrue(restResponse.isSuccess());
        deleteDummyUser(token, mockMvc);
    }


    @Test
    void deleteAccount() throws Exception {
        String token=getCreatedAndAuthenticatedDummyUserToken(mockMvc);

        // Send a DELETE request to delete the customer account
        MvcResult deleteResult=deleteDummyUser(token, mockMvc);

        // Verify the response
        RestResponse response=objectMapper.readValue(deleteResult.getResponse().getContentAsString(), RestResponse.class);
        assertTrue(response.isSuccess());
        assertNull(response.getData());
    }


    @Test
    void updateAccount() throws Exception {
        String token=getCreatedAndAuthenticatedDummyUserToken(mockMvc);
        CustomerSaveRequestDTO dummyUser=setDummyUser();
        Optional<Customer> customer=customerRepository.findByIdentityNo(dummyUser.getIdentityNo());
        CustomerUpdateRequestDTO customerUpdateRequestDTO=new CustomerUpdateRequestDTO();
        customerUpdateRequestDTO.setName("Updated Name");
        customerUpdateRequestDTO.setSurname("Updated Surname");
        customerUpdateRequestDTO.setBirthDate(customer.get().getBirthDate());
        customerUpdateRequestDTO.setIdentityNo(customer.get().getIdentityNo());
        customerUpdateRequestDTO.setPhoneNumber(customer.get().getPhoneNumber());
        customerUpdateRequestDTO.setPersonType(customer.get().getPersonType());
        customerUpdateRequestDTO.setMonthlyIncome(customer.get().getMonthlyIncome());
        String content=objectMapper.writeValueAsString(customerUpdateRequestDTO);
        MvcResult result=mockMvc.perform(
                        put(BASE_URL)
                                .header("Authorization", token)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        RestResponse restResponse=objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);
        boolean isSuccess=isRestResponseSuccess(result);
        assertTrue(isSuccess);
        deleteDummyUser(token, mockMvc);
    }

    @Test
    void findLoansByCustomerIdentityNoAndCustomerBirthDate() throws Exception {
        String token=getCreatedAndAuthenticatedDummyUserToken(mockMvc);
        long identityNo=setDummyUser().getIdentityNo();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyy-MM-dd");

        String birthDate=dateFormat.format(setDummyUser().getBirthDate());
        String findLoanUrl=BASE_URL+"/"+identityNo
                +"/"+birthDate+"/find-loans";
        MvcResult result=mockMvc.perform(get(findLoanUrl)
                        .header("Authorization", token)
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        RestResponse restResponse=objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);
        boolean isSuccess=isRestResponseSuccess(result);
        assertTrue(isSuccess);
        deleteDummyUser(token, mockMvc);
    }
}