package com.findoutmycreditscore.application.customer.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */


import com.findoutmycreditscore.application.customer.dto.CustomerDTO;
import com.findoutmycreditscore.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmycreditscore.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmycreditscore.application.customer.service.CustomerService;
import com.findoutmycreditscore.application.generic.dto.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<CustomerDTO>> getCustomerById(@PathVariable Long id) {
        CustomerDTO customerDTO = customerService.getByIdWithControl(id);

        return ResponseEntity.ok(RestResponse.of(customerDTO));
    }
    @PostMapping
    public ResponseEntity<RestResponse<CustomerDTO>> saveCustomer(@RequestBody CustomerSaveRequestDTO customerSaveRequestDTO) {
        CustomerDTO customerDTO = customerService.saveCustomer(customerSaveRequestDTO);
        return ResponseEntity.ok(RestResponse.of(customerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Object>> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerByIdWithControl(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

    @PutMapping
    public ResponseEntity<RestResponse<CustomerDTO>> updateCustomer(@RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        CustomerDTO customerDTO = customerService.updateCustomer(customerUpdateRequestDTO);
        return ResponseEntity.ok(RestResponse.of(customerDTO));
    }
}
