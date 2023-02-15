package com.findoutmyloan.application.loan.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.facade.LoanFacade;
import com.findoutmyloan.application.loan.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.generic.dto.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;
    private final LoanFacade loanFacade;
    @PostMapping
    public ResponseEntity<RestResponse<LoanDTO>> saveLoan(@RequestBody LoanSaveRequestDTO loanSaveRequestDTO) {
        LoanDTO loanDTO= loanService.saveLoan(loanSaveRequestDTO);
        return ResponseEntity.ok(RestResponse.of(loanDTO));
    }

    @PostMapping("/apply")
    public ResponseEntity<RestResponse<LoanDTO>> applyLoan(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        LoanDTO loanDTO= loanFacade.applyLoan(loanApplicationRequestDTO);
        return ResponseEntity.ok(RestResponse.of(loanDTO));
    }

}
