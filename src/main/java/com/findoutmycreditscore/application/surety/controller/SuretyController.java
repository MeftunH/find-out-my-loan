package com.findoutmycreditscore.application.surety.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.surety.dto.SuretyDTO;
import com.findoutmycreditscore.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmycreditscore.application.surety.service.SuretyService;
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
    @PostMapping("/save")
    public ResponseEntity<SuretyDTO> saveSurety(@RequestBody SuretySaveRequestDTO suretySaveRequestDTO) {
        SuretyDTO suretyDTO = suretyService.saveSurety(suretySaveRequestDTO);
         return ResponseEntity.ok(suretyDTO);
    }
}
