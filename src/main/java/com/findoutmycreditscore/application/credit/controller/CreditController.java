package com.findoutmycreditscore.application.credit.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.credit.dto.CreditDto;
import com.findoutmycreditscore.application.credit.dto.CreditSaveRequestDTO;
import com.findoutmycreditscore.application.credit.entity.Credit;
import com.findoutmycreditscore.application.credit.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/credit")
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;
    @PostMapping
    public ResponseEntity saveCredit(@RequestBody CreditSaveRequestDTO creditSaveRequestDTO) {
        CreditDto creditDto = creditService.saveCredit(creditSaveRequestDTO);
        return ResponseEntity.ok(creditDto);
    }
}
