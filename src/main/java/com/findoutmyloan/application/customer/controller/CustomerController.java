package com.findoutmyloan.application.customer.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */


import com.findoutmyloan.application.customer.dto.CustomerResultDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.customer.service.CustomerService;
import com.findoutmyloan.application.generic.dto.RestResponse;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.security.service.AuthenticationService;
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

    @GetMapping
    public ResponseEntity<RestResponse<MappingJacksonValue>> getMyAccountInformation() {
        CustomerResultDTO customerResultDTO=customerService.getByIdWithControl(authenticationService.getCurrentCustomer().getId());
        WebMvcLinkBuilder link=WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).deleteAccount());

        EntityModel entityModel=EntityModel.of(customerResultDTO);

        entityModel.add(link.withRel("deleteCustomerById"));

        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(entityModel);
        return ResponseEntity.ok(RestResponse.of(mappingJacksonValue));
    }
    @DeleteMapping()
    public ResponseEntity<RestResponse<Object>> deleteAccount() {
        customerService.deleteAccountByIdControl(authenticationService.getCurrentCustomer().getId());
        return ResponseEntity.ok(RestResponse.empty());
    }

    @PutMapping
    public ResponseEntity<RestResponse<CustomerResultDTO>> updateAccount(@RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        CustomerResultDTO customerResultDTO=customerService.updateCustomer(customerUpdateRequestDTO);
        return ResponseEntity.ok(RestResponse.of(customerResultDTO));
    }

    @GetMapping("/{identityNo}/{birthday}/find-loans")
    public ResponseEntity<RestResponse<List<LoanDTO>>> findLoansByCustomerIdentityNoAndCustomerBirthDate(@PathVariable long identityNo,
                                                                                                         @PathVariable("birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthDate) throws GeneralSecurityException {
        return ResponseEntity.ok(RestResponse.of(customerService.findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, birthDate)));
    }
}
