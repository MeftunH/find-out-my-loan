package com.findoutmycreditscore.application.creditscore.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.creditscore.dto.CreditScoreDTO;
import com.findoutmycreditscore.application.creditscore.dto.CreditScoreSaveRequestDTO;
import com.findoutmycreditscore.application.creditscore.service.CreditScoreApiService;
import com.findoutmycreditscore.application.generic.dto.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/credit-score")
public class CreditScoreApiRestController {
    private final CreditScoreApiService creditScoreApiService;

    public CreditScoreApiRestController(CreditScoreApiService creditScoreApiService) {
        this.creditScoreApiService=creditScoreApiService;
    }

   //post mapping because we are saving the credit score and it returns us the credit score
    @PostMapping
    public ResponseEntity<RestResponse<CreditScoreDTO>> getCreditScore(@RequestBody CreditScoreSaveRequestDTO creditScoreSaveRequestDTO) {
        CreditScoreDTO creditScore = creditScoreApiService.getCreditScore(creditScoreSaveRequestDTO);
        return ResponseEntity.ok(RestResponse.of(creditScore));
    }

}
