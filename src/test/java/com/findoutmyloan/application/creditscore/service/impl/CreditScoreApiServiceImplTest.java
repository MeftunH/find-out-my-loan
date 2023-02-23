package com.findoutmyloan.application.creditscore.service.impl;

import com.findoutmyloan.application.creditscore.config.RestTemplateConfiguration;
import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;
import com.findoutmyloan.application.creditscore.dto.CreditScoreResponseDTO;
import com.findoutmyloan.application.creditscore.service.CreditScoreApiService;
import com.findoutmyloan.application.customer.dto.CustomerCreditScoreRequestDTO;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application.properties")
public class CreditScoreApiServiceImplTest {
    @Value("${application.CREDIT_SCORE_ENDPOINT_URL}")
    private String CREDIT_SCORE_ENDPOINT_URL;
    private CreditScoreApiServiceImpl service;

    @Mock
    private RestTemplateConfiguration restTemplateConfiguration;

    @BeforeEach
    public void setUp() {
        restTemplateConfiguration = mock(RestTemplateConfiguration.class);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        service = new CreditScoreApiServiceImpl(restTemplateConfiguration,httpHeaders);
    }

    @Test
     void shouldGetCreditScore() {
        CreditScoreRequestDTO requestDTO = mock(CreditScoreRequestDTO.class);
        CustomerCreditScoreRequestDTO customerCreditScoreRequestDTO = mock(CustomerCreditScoreRequestDTO.class);

        CreditScoreResponseDTO responseDTO = new CreditScoreResponseDTO();
        responseDTO.setCreditScore(486);
        responseDTO.setCustomerIdentityNo(0);
        ResponseEntity<CreditScoreResponseDTO> responseEntity = ResponseEntity.ok(responseDTO);
        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        when(restTemplateConfiguration.restTemplate()).thenReturn(mockRestTemplate);

        when(restTemplateConfiguration.restTemplate().postForEntity(CREDIT_SCORE_ENDPOINT_URL, requestDTO, CreditScoreResponseDTO.class))
                .thenReturn(responseEntity);

        CreditScoreResponseDTO actualResponse = service.getCreditScore(requestDTO);
        Assertions.assertEquals(responseDTO, actualResponse);
    }
    @Test
    void shouldGetCreditScoreWithServerError() {
        // create a mock RestTemplateConfiguration object
        RestTemplateConfiguration restTemplateConfiguration = mock(RestTemplateConfiguration.class);

        // set up the mock RestTemplateConfiguration object to return a mock RestTemplate
        RestTemplate restTemplate = mock(RestTemplate.class);

        CreditScoreRequestDTO creditScoreDTO = mock(CreditScoreRequestDTO.class);
        CustomerCreditScoreRequestDTO customerCreditScoreDTO = mock(CustomerCreditScoreRequestDTO.class);

        // call the method under test and catch the expected exception
        Assertions.assertThrows(NullPointerException.class, () -> service.getCreditScore(creditScoreDTO));
    }
}