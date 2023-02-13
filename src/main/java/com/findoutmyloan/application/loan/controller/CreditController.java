package com.findoutmyloan.application.loan.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.generic.dto.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/credit")
@RequiredArgsConstructor
public class CreditController {
    private final LoanService loanService;
    @PostMapping
    public ResponseEntity<RestResponse<LoanDTO>> saveCredit(@RequestBody LoanSaveRequestDTO loanSaveRequestDTO) {
        LoanDTO loanDTO= loanService.saveCredit(loanSaveRequestDTO);
        return ResponseEntity.ok(RestResponse.of(loanDTO));
    }
}
