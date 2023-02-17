package com.findoutmyloan.application.creditscore.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.creditscore.dto.CreditScoreResponseDTO;
import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;
import com.findoutmyloan.application.creditscore.service.CreditScoreApiService;
import com.findoutmyloan.application.generic.dto.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/credit-score")
public class CreditScoreApiRestController {
    private final CreditScoreApiService creditScoreApiService;

    public CreditScoreApiRestController(CreditScoreApiService creditScoreApiService) {
        this.creditScoreApiService=creditScoreApiService;
    }
    @Operation(tags="Credit Score", summary = "Get Credit Score")
   //post mapping because we are saving the credit score and it returns us the credit score
    @PostMapping
    public ResponseEntity<RestResponse<CreditScoreResponseDTO>> getCreditScore(@RequestBody CreditScoreRequestDTO creditScoreRequestDTO) {
        CreditScoreResponseDTO creditScore = creditScoreApiService.getCreditScore(creditScoreRequestDTO);
        return ResponseEntity.ok(RestResponse.of(creditScore));
    }

}
