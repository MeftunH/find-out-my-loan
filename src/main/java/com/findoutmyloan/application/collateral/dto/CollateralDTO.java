package com.findoutmyloan.application.collateral.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.enums.CollateralType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CollateralDTO implements Serializable {
    private CollateralType collateralType;
    private long customerId;
    private float worth;
    private Date baseAdditionalFieldsCreatedDate;
    private Date baseAdditionalFieldsUpdatedDate;
}