package com.findoutmyloan.application;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.generic.dto.RestResponse;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.security.dto.SecurityLoginRequestDTO;
import com.findoutmyloan.application.util.DateUtil;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class BaseTest {
    private static final String LOGIN_URL="/auth/login";
    private static final String REGISTER_URL="/auth/register";
    protected ObjectMapper objectMapper=new ObjectMapper();

    private static boolean isRestResponseSuccess(RestResponse restResponse) {
        boolean isSuccess=restResponse.isSuccess();
        return isSuccess;
    }

    protected static void setSecurityDetails(CustomerSaveRequestDTO customerSaveRequestDTO) {
        SecurityLoginRequestDTO securityLoginRequestDTO=new SecurityLoginRequestDTO();
        //get first customer from database
        securityLoginRequestDTO.setIdentityNo(customerSaveRequestDTO.getIdentityNo());
        securityLoginRequestDTO.setPassword(customerSaveRequestDTO.getPassword());
    }

    protected static CustomerSaveRequestDTO setDummyUser() throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.parse("2001-11-11T13:15:30");
        Instant instant = localDateTime.atZone(ZoneId.of("Europe/Istanbul")).toInstant();
        Date dateFromInstant = Date.from(instant);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");




        CustomerSaveRequestDTO customerSaveRequestDTO=new CustomerSaveRequestDTO();
        customerSaveRequestDTO.setName("DummyTestUser");
        customerSaveRequestDTO.setSurname("DummyTestUser");
        customerSaveRequestDTO.setIdentityNo(78611814040L);
        customerSaveRequestDTO.setPassword("123Mm");
        customerSaveRequestDTO.setMonthlyIncome(6000f);
        customerSaveRequestDTO.setBirthDate(dateFromInstant);
        customerSaveRequestDTO.setPhoneNumber("1234222223");
        customerSaveRequestDTO.setPersonType(PersonType.CUSTOMER);
        customerSaveRequestDTO.setBaseAdditionalFieldsCreatedDate(DateUtil.convertToDate(LocalDate.now()));
        customerSaveRequestDTO.setBaseAdditionalFieldsUpdatedDate(DateUtil.convertToDate(LocalDate.now()));
        return customerSaveRequestDTO;
    }

    protected String getCreatedAndAuthenticatedDummyUserToken(MockMvc mockMvc) throws Exception {
        CustomerSaveRequestDTO customerSaveRequestDTO=setDummyUser();
        createDummyUser(customerSaveRequestDTO, mockMvc);
        String jsonRequest=objectMapper.writeValueAsString(customerSaveRequestDTO);
        getDummyUserLoginResult(jsonRequest, mockMvc);
        setSecurityDetails(customerSaveRequestDTO);
        String loginJsonRequest=objectMapper.writeValueAsString(customerSaveRequestDTO);
        MvcResult loginResult=mockMvc.perform(
                        post(LOGIN_URL).contentType(MediaType.APPLICATION_JSON).content(loginJsonRequest))
                .andReturn();
        String token=objectMapper.readValue(loginResult.getResponse().getContentAsString(), RestResponse.class).getData().toString();

        return token;
    }
    protected boolean isRestResponseSuccess(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        RestResponse restResponse=getRestResponse(result);
        boolean isSuccess=isRestResponseSuccess(restResponse);
        return isSuccess;
    }

    private RestResponse getRestResponse(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        RestResponse restResponse=objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);
        return restResponse;
    }

    protected MvcResult createDummyUser(CustomerSaveRequestDTO customerSaveRequestDTO, MockMvc mockMvc) throws Exception {
        String jsonRequest=objectMapper.writeValueAsString(customerSaveRequestDTO);
        MvcResult result=mockMvc.perform(
                        post(REGISTER_URL).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();
        return result;
    }

    protected MvcResult getDummyUserLoginResult(String jsonRequest, MockMvc mockMvc) throws Exception {
        MvcResult result=mockMvc.perform(
                        post(LOGIN_URL).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();
        return result;
    }

    protected MvcResult deleteDummyUser(String token, MockMvc mockMvc) throws Exception {
        MvcResult deleteResult=mockMvc.perform(
                        delete("/api/v1/customer")
                                .header("Authorization", "Bearer "+token))
                .andExpect(status().isOk())
                .andReturn();
        return deleteResult;
    }

}
