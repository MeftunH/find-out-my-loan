package com.findoutmyloan.application.security.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerResponseDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.generic.dto.RestResponse;
import com.findoutmyloan.application.log.SingletonLogger;
import com.findoutmyloan.application.security.dto.SecurityLoginRequestDTO;
import com.findoutmyloan.application.security.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final SingletonLogger logger=SingletonLogger.getInstance();

    @Operation(tags = "Authentication", summary = "Login", description = "Login with identity no and password",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (
                            content = @io.swagger.v3.oas.annotations.media.
                                    Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.
                                            Schema(implementation = SecurityLoginRequestDTO.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Login credentials",
                                                    summary = "For jwt token",
                                                    description = "identityNo and password",
                                                    value = "{\n"+
                                                            "\t \"identityNo\":89565435274,\n"+
                                                            "\t\t\"password\": \"123MmM\"\n"+
                                                            "}"
                                            )
                                    }
                            )
                    )
    )
    @PostMapping("/login")
    public ResponseEntity<RestResponse<String>> login(@RequestBody SecurityLoginRequestDTO securityLoginRequestDTO) {
        String token=authenticationService.login(securityLoginRequestDTO);
        logger.info("Login is successful by token: " .concat(token));
        return ResponseEntity.ok(RestResponse.of(token));
    }

    @Operation(tags = "Authentication", summary = "Register", description = "Register",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (
                            content = @io.swagger.v3.oas.annotations.media.
                                    Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.
                                            Schema(implementation = CustomerSaveRequestDTO.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new user",
                                                    summary = "New User Example",
                                                    description = "Complete request with all available fields for user",
                                                    value = "{\n"+
                                                            "\t\"name\": \"John\",\n"+
                                                            "  \"surname\": \"Doe\",\n"+
                                                            "  \"identityNo\": 16079248434,\n"+
                                                            "\"birthDate\": \"1999-01-01\",   \n"+
                                                            "  \"phoneNumber\": \"5655555556\",\n"+
                                                            "  \"personType\": \"CUSTOMER\",\n"+
                                                            "  \"monthlyIncome\": 5000.0,\n"+
                                                            "  \"paybackGuaranteeType\": \"COLLATERAL\",\n"+
                                                            "\t\"password\": \"123MmM\"\n"+
                                                            "}"
                                            )
                                    }
                            )
                    )
    )
    @PostMapping("/register")
    public ResponseEntity<RestResponse<CustomerResponseDTO>> register(@RequestBody CustomerSaveRequestDTO customerSaveRequestDTO) {
        CustomerResponseDTO customerResponseDTO=authenticationService.register(customerSaveRequestDTO);
        logger.info("Register is successful by customer: "+customerResponseDTO);
        return ResponseEntity.ok(RestResponse.of(customerResponseDTO));
    }
}
