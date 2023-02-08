package com.findoutmycreditscore.application.creditscore.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.creditscore.config.RestTemplateConfiguration;
import com.findoutmycreditscore.application.creditscore.entity.CreditScore;
import com.findoutmycreditscore.application.creditscore.service.CreditScoreApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public int getCreditScore() {
       httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        ResponseEntity<CreditScore> response=restTemplateConfiguration.restTemplate(restTemplateConfiguration.restTemplateBuilder()).getForEntity(CREDIT_SCORE_ENDPOINT_URL, CreditScore.class, entity);
        return Objects.requireNonNull(response.getBody()).getCreditScore();

    }
}
