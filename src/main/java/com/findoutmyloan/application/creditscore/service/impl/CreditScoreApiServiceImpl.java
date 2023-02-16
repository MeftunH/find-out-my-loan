package com.findoutmyloan.application.creditscore.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.creditscore.config.RestTemplateConfiguration;
import com.findoutmyloan.application.creditscore.dto.CreditScoreResponseDTO;
import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;
import com.findoutmyloan.application.creditscore.service.CreditScoreApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditScoreApiServiceImpl implements CreditScoreApiService {
    private final RestTemplateConfiguration restTemplateConfiguration;
    private final HttpHeaders httpHeaders;
    public CreditScoreApiServiceImpl(RestTemplateConfiguration restTemplateConfiguration, HttpHeaders httpHeaders) {
        this.restTemplateConfiguration=restTemplateConfiguration;
        this.httpHeaders=httpHeaders;
    }

    @Value("${application.CREDIT_SCORE_ENDPOINT_URL}")
    private String CREDIT_SCORE_ENDPOINT_URL;

    @Override
    public CreditScoreResponseDTO getCreditScore(CreditScoreRequestDTO creditScoreDTO) {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        ResponseEntity<CreditScoreResponseDTO> response=restTemplateConfiguration.restTemplate().
                postForEntity(CREDIT_SCORE_ENDPOINT_URL, creditScoreDTO, CreditScoreResponseDTO.class);
        return response.getBody();
    }
}
