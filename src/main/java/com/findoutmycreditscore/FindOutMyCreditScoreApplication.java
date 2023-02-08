package com.findoutmycreditscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(excludeFilters =
        {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EnableSpringConfigured.class)})

public class FindOutMyCreditScoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindOutMyCreditScoreApplication.class, args);
    }

}
