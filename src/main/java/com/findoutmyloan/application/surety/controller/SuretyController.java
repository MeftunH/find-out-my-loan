package com.findoutmyloan.application.surety.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.dto.RestResponse;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.surety.dto.SuretyDTO;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.service.SuretyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/surety")
@RequiredArgsConstructor
public class SuretyController {
   private final SuretyService suretyService;
   @Operation(tags="Surety", summary = "Save Surety",
           requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody
                   (
                           content = @io.swagger.v3.oas.annotations.media.
                                   Content(
                                   mediaType = "application/json",
                                   schema = @io.swagger.v3.oas.annotations.media.
                                           Schema(implementation = SuretySaveRequestDTO.class),
                                   examples = {
                                           @ExampleObject(
                                                   name = "save new surety",
                                                   summary = "New surety Example",
                                                   description = "Complete request with all available fields for surety",
                                                   value ="{\n"+
                                                           " \"name\": \"Jane\",\n"+
                                                           "  \"surname\": \"Doe\",\n"+
                                                           "\t\"suretyType\": \"JOINT\",\n"+
                                                           "  \"identityNo\": 47869325622,\n"+
                                                           "  \"birthDate\": \"1980-01-01\",\n"+
                                                           "  \"phoneNumber\": \"58124559\",\n"+
                                                           "  \"personType\": \"SURETY\"\n"+
                                                           "}"
                                           )
                                   }
                           )
                   ))
   @PostMapping
    public ResponseEntity<RestResponse<SuretyDTO>> saveSurety(@RequestBody SuretySaveRequestDTO suretySaveRequestDTO) {
        SuretyDTO suretyDTO = suretyService.saveSurety(suretySaveRequestDTO);
         return ResponseEntity.ok(RestResponse.of(suretyDTO));
    }
}
