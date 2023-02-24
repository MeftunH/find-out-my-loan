package com.findoutmyloan.application.loan.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.facade.dto.CustomerLoanResponseDTO;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.facade.service.LoanFacade;
import com.findoutmyloan.application.generic.dto.RestResponse;
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

    @Operation(tags = "Loan", summary = "Save Loan",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (
                            content = @io.swagger.v3.oas.annotations.media.
                                    Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.
                                            Schema(implementation = LoanSaveRequestDTO.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "save new loan",
                                                    summary = "New Loan Example",
                                                    description = "Complete request with all available fields for loan",
                                                    value = "{\n"+
                                                            "    \"paybackGuaranteeType\": \"COLLATERAL\",\n"+
                                                            "    \"amount\": 5000.0,\n"+
                                                            "    \"result\": \"APPROVED\"\n"+
                                                            "}"
                                            )
                                    }
                            )
                    )
    )
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
                                            Schema(implementation = LoanApplicationRequestDTO.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Apply for loan by surety",
                                                    summary = "New Apply for loan by surety Example",
                                                    description = "Complete request with all available fields for loan",
                                                    value = "{\n"+
                                                            "  \"suretyName\": \"Jane\",\n"+
                                                            "  \"suretySurname\": \"Doe\",\n"+
                                                            "\t\"suretyType\": \"JOINT\",\n"+
                                                            "  \"suretyIdentityNo\": 47701739716,\n"+
                                                            "  \"suretyBirthDate\": \"1980-01-01\",\n"+
                                                            "  \"suretyPhoneNumber\": \"55125559\",\n"+
                                                            "  \"suretyPersonType\": \"SURETY\",\n"+
                                                            "  \"paybackGuaranteeType\": \"SURETY\"\n"+
                                                            "}"
                                            ),
                                            @ExampleObject(
                                                    name = "Apply for loan by collateral",
                                                    summary = "New Apply for loan by collateral Example",
                                                    description = "Complete request with all available fields for loan",
                                                    value = "{\n"+
                                                            "  \"paybackGuaranteeType\": \"COLLATERAL\",\n"+
                                                            "\t\"collateralType\": \"MONEY\",\n"+
                                                            "\t\"collateralWorth\": 1928.0\n"+
                                                            "}"
                                            ),
                                            @ExampleObject(
                                                    name = "Apply for loan by surety and collateral",
                                                    summary = "New Apply for loan by surety and collateral Example",
                                                    description = "Complete request with all available fields for loan",
                                                    value = "{\n"+
                                                            "  \"suretyName\": \"Jane\",\n"+
                                                            "  \"suretySurname\": \"Doe\",\n"+
                                                            "\t\"suretyType\": \"JOINT\",\n"+
                                                            "  \"suretyIdentityNo\": 97898017852,\n"+
                                                            "  \"suretyBirthDate\": \"1980-01-01\",\n"+
                                                            "  \"suretyPhoneNumber\": \"51665547\",\n"+
                                                            "  \"suretyPersonType\": \"SURETY\",\n"+
                                                            "  \"paybackGuaranteeType\": \"ALL_OF_THEM\",\n"+
                                                            "\t\t\"collateralType\": \"MONEY\",\n"+
                                                            "\t\"collateralWorth\": 2000.0\n"+
                                                            "}"
                                            )
                                    }
                            )
                    )
    )
    @PostMapping("/apply")
    public ResponseEntity<RestResponse<CustomerLoanResponseDTO>> applyLoan(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        CustomerLoanResponseDTO loanDTO=loanFacade.applyLoan(loanApplicationRequestDTO);
        return ResponseEntity.ok(RestResponse.of(loanDTO));
    }

}
