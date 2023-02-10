package com.findoutmycreditscore.application.customer.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */


import com.findoutmycreditscore.application.customer.dto.CustomerDTO;
import com.findoutmycreditscore.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmycreditscore.application.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

   @PostMapping("/save")
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerSaveRequestDTO customerSaveRequestDTO) {
        CustomerDTO customerDTO = customerService.saveCustomer(customerSaveRequestDTO);
        return ResponseEntity.ok(customerDTO);
    }
}
