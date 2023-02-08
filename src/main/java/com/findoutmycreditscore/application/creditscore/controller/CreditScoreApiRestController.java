package com.findoutmycreditscore.application.creditscore.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.creditscore.service.CreditScoreApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/credit-score")
public class CreditScoreApiRestController {
    private final CreditScoreApiService creditScoreApiService;

    public CreditScoreApiRestController(CreditScoreApiService creditScoreApiService) {
        this.creditScoreApiService=creditScoreApiService;
    }

    @GetMapping
    public ResponseEntity<Integer> getCreditScore() {
        int creditScore = creditScoreApiService.getCreditScore();
        return new ResponseEntity<>(creditScore, HttpStatus.OK);
    }

}
