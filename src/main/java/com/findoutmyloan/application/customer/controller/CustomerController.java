package com.findoutmyloan.application.customer.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */


import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.customer.service.CustomerService;
import com.findoutmyloan.application.generic.dto.RestResponse;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<CustomerDTO>> getCustomerById(@PathVariable Long id) {
        CustomerDTO customerDTO=customerService.getByIdWithControl(id);

        return ResponseEntity.ok(RestResponse.of(customerDTO));
    }

    @PostMapping
    public ResponseEntity<RestResponse<MappingJacksonValue>> saveCustomer(@RequestBody CustomerSaveRequestDTO customerSaveRequestDTO) {
        CustomerDTO customerDTO=customerService.saveCustomer(customerSaveRequestDTO);

        WebMvcLinkBuilder link=WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getCustomerById(customerDTO.getId()));

        EntityModel entityModel=EntityModel.of(customerDTO);

        entityModel.add(link.withRel("getCustomerById"));

        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(entityModel);

        return ResponseEntity.ok(RestResponse.of(mappingJacksonValue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Object>> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerByIdWithControl(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

    @PutMapping
    public ResponseEntity<RestResponse<CustomerDTO>> updateCustomer(@RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        CustomerDTO customerDTO=customerService.updateCustomer(customerUpdateRequestDTO);
        return ResponseEntity.ok(RestResponse.of(customerDTO));
    }

    @GetMapping("/{identityNo}/{birthday}/find-loans")
    public ResponseEntity<RestResponse<List<LoanDTO>>> findLoansByCustomerIdentityNoAndCustomerBirthDate(@PathVariable long identityNo,
                                                                                                         @PathVariable("birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthDate) {
        return ResponseEntity.ok(RestResponse.of(customerService.findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, birthDate)));
    }
}
