package com.findoutmyloan.application.customer.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */


import com.findoutmyloan.application.customer.dto.CustomerResponseDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.customer.service.CustomerService;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.generic.dto.RestResponse;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.security.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final AuthenticationService authenticationService;

    @Operation(tags = "Customer", summary = "Get my account information", description = "Get my account information")
    @GetMapping
    public ResponseEntity<RestResponse<MappingJacksonValue>> getMyAccountInformation() {
        CustomerResponseDTO customerResponseDTO=customerService.getByIdWithControl(authenticationService.getCurrentCustomer().getId());
        WebMvcLinkBuilder link=WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).deleteAccount());

        EntityModel<CustomerResponseDTO> entityModel=EntityModel.of(customerResponseDTO);

        entityModel.add(link.withRel("deleteCustomerById"));

        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(entityModel);
        return ResponseEntity.ok(RestResponse.of(mappingJacksonValue));
    }

    @Operation(tags = "Customer", summary = "Delete my account", description = "Delete my account")
    @DeleteMapping()
    public ResponseEntity<RestResponse<Object>> deleteAccount() {
        customerService.deleteAccountByIdControl(authenticationService.getCurrentCustomer().getId());
        return ResponseEntity.ok(RestResponse.empty());
    }

    @Operation(tags = "Customer", summary = "Update my account", description = "Update my account",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (
                            content = @io.swagger.v3.oas.annotations.media.
                                    Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.
                                            Schema(implementation = CustomerUpdateRequestDTO.class)
                            ))
    )
    @PutMapping
    public ResponseEntity<RestResponse<CustomerResponseDTO>> updateAccount(@RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        CustomerResponseDTO customerResponseDTO=customerService.updateCustomer(customerUpdateRequestDTO);
        return ResponseEntity.ok(RestResponse.of(customerResponseDTO));
    }

    @Operation(tags = "Customer", summary = "Find loans by customer identity number and customer birth date", description = "Find loans by customer identity number and customer birth date")
    @GetMapping("/{identityNo}/{birthday}/find-loans")
    public ResponseEntity<RestResponse<List<LoanDTO>>> findLoansByCustomerIdentityNoAndCustomerBirthDate(@Parameter(description = "70632842798") @PathVariable long identityNo,
                                                                                                         @Parameter(description = "01-01-1980") @PathVariable("birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthDate) {
        return ResponseEntity.ok(RestResponse.of(customerService.findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, birthDate)));
    }
}
