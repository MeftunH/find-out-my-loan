package com.findoutmyloan.application.loan.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.facade.LoanFacade;
import com.findoutmyloan.application.generic.dto.RestResponse;
import com.findoutmyloan.application.loan.dto.CustomerLoanResponseDTO;
import com.findoutmyloan.application.loan.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;
    private final LoanFacade loanFacade;

    @Operation(tags = "Loan", summary = "Save Loan")
    @PostMapping
    public ResponseEntity<RestResponse<LoanDTO>> saveLoan(@RequestBody LoanSaveRequestDTO loanSaveRequestDTO) {
        LoanDTO loanDTO=loanService.saveLoan(loanSaveRequestDTO);
        return ResponseEntity.ok(RestResponse.of(loanDTO));
    }

    @Operation(tags = "Loan", summary = "Apply Loan"
            , description = "Apply Loan",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (
                            content = @io.swagger.v3.oas.annotations.media.
                                    Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.
                                            Schema(implementation = CustomerUpdateRequestDTO.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "CustomerUpdateRequestDTO",
                                                    value = "{\n"+
                                                            "  \"customerName\": John,\n"+
                                                            "  \"customerSurname\": \"Doe\",\n"+
                                                            "  \"customerIdentityNo\": \"123456784\",\n"+
                                                            "  \"customerBirthDate\": 1999-01-01,\n"+
                                                            "  \"customerPhoneNumber\": \"5555555555\",\n"+
                                                            "  \"customerPersonType\": \"CUSTOMER\",\n"+
                                                            "  \"customerMonthlyIncome\": \"5000.0\",\n"+
                                                            "  \"suretyName\": \"Jane\",\n"+
                                                            "  \"suretySurname\": \"Doe\",\n"+
                                                            "  \"suretyType\": \"JOINT\",\n"+
                                                            "  \"suretyIdentityNo\": \"987654321\",\n"+
                                                            "  \"suretyBirthDate\": \"01-01-1975\",\n"+
                                                            "  \"suretyPhoneNumber\": \"55555557\",\n"+
                                                            "  \"suretyPersonType\": \"SURETY\",\n"+
                                                            "  \"paybackGuaranteeType\": \"SURETY\",\n"
                                            ),
                                            @ExampleObject(
                                                    name = "CustomerUpdateRequestDTO",
                                                    value = "{\n"+
                                                            "  \"customerName\": John,\n"+
                                                            "  \"customerSurname\": \"Doe\",\n"+
                                                            "  \"customerIdentityNo\": \"123456784\",\n"+
                                                            "  \"customerBirthDate\": 1999-01-01,\n"+
                                                            "  \"customerPhoneNumber\": \"5555555555\",\n"+
                                                            "  \"customerPersonType\": \"CUSTOMER\",\n"+
                                                            "  \"customerMonthlyIncome\": \"5000.0\",\n"+
                                                            "  \"collateralType\": \"MONEY\",\n"+
                                                            "  \"collateralWorth\": \"11100.0\",\n"+
                                                            "  \"paybackGuaranteeType\": \"COLLATERAL\",\n"
                                            )
                                    }
                            ))
    )
    @PostMapping("/apply")
    public ResponseEntity<RestResponse<CustomerLoanResponseDTO>> applyLoan(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        CustomerLoanResponseDTO loanDTO=loanFacade.applyLoan(loanApplicationRequestDTO);
        return ResponseEntity.ok(RestResponse.of(loanDTO));
    }

}
