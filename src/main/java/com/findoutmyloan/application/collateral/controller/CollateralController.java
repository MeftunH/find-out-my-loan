package com.findoutmyloan.application.collateral.controller;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralDTO;
import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.service.CollateralService;
import com.findoutmyloan.application.generic.dto.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collateral")
public class CollateralController {
    private final CollateralService collateralService;

    @Operation(tags = "Collateral", summary = "Save Collateral", description = "Save Collateral",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (
                            content = @io.swagger.v3.oas.annotations.media.
                                    Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.
                                            Schema(implementation = CollateralSaveRequestDTO.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new collateral",
                                                    summary = "New Collateral Example",
                                                    description = "Save new collateral",
                                                    value = "{\n"+
                                                            "collateralType\": \"MONEY\",\n"+
                                                            "\t\"worth\": 1928.0\n"+
                                                            "}"
                                            )
                                    }
                            )
                    )
    )
    @PostMapping
    public ResponseEntity<RestResponse<CollateralDTO>> saveCollateral(@RequestBody CollateralSaveRequestDTO collateralSaveRequestDTO) {
        CollateralDTO collateralDTO=collateralService.saveCollateral(collateralSaveRequestDTO);
        return ResponseEntity.ok(RestResponse.of(collateralDTO));
    }
}
