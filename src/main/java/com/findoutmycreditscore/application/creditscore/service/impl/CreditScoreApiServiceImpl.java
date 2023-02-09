package com.findoutmycreditscore.application.creditscore.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.creditscore.config.RestTemplateConfiguration;
import com.findoutmycreditscore.application.creditscore.dto.CreditScoreDTO;
import com.findoutmycreditscore.application.creditscore.dto.CreditScoreSaveRequestDTO;
import com.findoutmycreditscore.application.creditscore.entity.CreditScore;
import com.findoutmycreditscore.application.creditscore.service.CreditScoreApiService;
import org.apache.logging.log4j.util.Base64Util;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Service
public class CreditScoreApiServiceImpl implements CreditScoreApiService {
    private final RestTemplateConfiguration restTemplateConfiguration;
    private final HttpHeaders httpHeaders;
    public CreditScoreApiServiceImpl(RestTemplateConfiguration restTemplateConfiguration, HttpHeaders httpHeaders) {
        this.restTemplateConfiguration=restTemplateConfiguration;
        this.httpHeaders=httpHeaders;
    }

    @Value("${app.CREDIT_SCORE_ENDPOINT_URL}")
    private String CREDIT_SCORE_ENDPOINT_URL;

    @Override
    public CreditScoreDTO getCreditScore(CreditScoreSaveRequestDTO creditScoreDTO) {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        ResponseEntity<CreditScoreDTO> response=restTemplateConfiguration.restTemplate().
                postForEntity(CREDIT_SCORE_ENDPOINT_URL, creditScoreDTO, CreditScoreDTO.class);
        return response.getBody();
    }
}
