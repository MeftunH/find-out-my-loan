package com.findoutmyloan.application.collateral.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.CollateralType;
import lombok.Data;

import java.io.Serializable;

@Data
public class CollateralDTO implements Serializable {
    private CollateralType collateralType;
    private long customerId;
    private float worth;
}