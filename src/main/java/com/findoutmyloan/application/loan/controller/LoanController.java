package com.findoutmyloan.application.loan.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.facade.service.LoanFacade;
import com.findoutmyloan.application.facade.dto.CustomerLoanResponseDTO;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.generic.dto.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;
    private final LoanFacade loanFacade;

    @Operation(tags="Loan", summary = "Save Loan")
    @PostMapping
    public ResponseEntity<RestResponse<LoanDTO>> saveLoan(@RequestBody LoanSaveRequestDTO loanSaveRequestDTO) {
        LoanDTO loanDTO= loanService.saveLoan(loanSaveRequestDTO);
        return ResponseEntity.ok(RestResponse.of(loanDTO));
    }

    @Operation(tags="Loan", summary = "Apply Loan"
            , description = "Apply Loan",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody
            (
                    content = @io.swagger.v3.oas.annotations.media.
                            Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.
                                    Schema(implementation = CustomerUpdateRequestDTO.class)
                    ))
    )
    @PostMapping("/apply")
    public ResponseEntity<RestResponse<CustomerLoanResponseDTO>> applyLoan(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        CustomerLoanResponseDTO loanDTO= loanFacade.applyLoan(loanApplicationRequestDTO);
        return ResponseEntity.ok(RestResponse.of(loanDTO));
    }

}
