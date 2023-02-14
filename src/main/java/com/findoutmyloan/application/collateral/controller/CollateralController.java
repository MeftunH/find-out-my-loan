package com.findoutmyloan.application.collateral.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralDTO;
import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.service.CollateralService;
import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.generic.dto.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collateral")
public class CollateralController
{
    private final CollateralService collateralService;
    @PostMapping
    public ResponseEntity<RestResponse<CollateralDTO>> saveCollateral(@RequestBody CollateralSaveRequestDTO collateralSaveRequestDTO)
    {
        CollateralDTO collateralDTO = collateralService.saveCollateral(collateralSaveRequestDTO);
        return ResponseEntity.ok(RestResponse.of(collateralDTO));
    }
}
