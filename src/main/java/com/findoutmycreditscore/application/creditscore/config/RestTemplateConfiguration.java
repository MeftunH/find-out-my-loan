package com.findoutmycreditscore.application.creditscore.config;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        return new HttpHeaders();
    }

    public RestTemplateBuilder restTemplateBuilder()
    {
        return new RestTemplateBuilder();
    }
}
