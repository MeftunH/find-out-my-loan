package com.findoutmyloan.application.surety.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.surety.dto.SuretyDTO;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.service.SuretyService;
import io.swagger.v3.oas.annotations.Operation;
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
   @Operation(tags="Surety", summary = "Save Surety")
   @PostMapping("/save")
    public ResponseEntity<SuretyDTO> saveSurety(@RequestBody SuretySaveRequestDTO suretySaveRequestDTO) {
        SuretyDTO suretyDTO = suretyService.saveSurety(suretySaveRequestDTO);
         return ResponseEntity.ok(suretyDTO);
    }
}
