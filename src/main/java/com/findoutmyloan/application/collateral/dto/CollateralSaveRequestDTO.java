package com.findoutmyloan.application.collateral.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.enums.CollateralType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CollateralSaveRequestDTO implements Serializable {
    private CollateralType collateralType;
    private long customerId;
    private float worth;
}
