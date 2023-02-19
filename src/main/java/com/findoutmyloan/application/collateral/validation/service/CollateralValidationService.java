package com.findoutmyloan.application.collateral.validation.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.entity.Collateral;

public interface CollateralValidationService {
    void validateAreFieldsNonNull(Collateral collateral);
    void validateWorthIsPositive(float worth);
    void validateCollateral(Collateral collateral);
}
