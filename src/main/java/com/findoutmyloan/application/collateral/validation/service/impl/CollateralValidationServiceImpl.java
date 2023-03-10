package com.findoutmyloan.application.collateral.validation.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */


import com.findoutmyloan.application.collateral.entity.Collateral;
import com.findoutmyloan.application.collateral.enums.CollateralErrorMessage;
import com.findoutmyloan.application.collateral.validation.service.CollateralValidationService;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.log.SingletonLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollateralValidationServiceImpl implements CollateralValidationService {
    private final SingletonLogger logger=SingletonLogger.getInstance();

    @Override
    public void validateAreFieldsNonNull(Collateral collateral) {
        boolean isNull=collateral.getCollateralType()==null||String.valueOf(collateral.getWorth()).equals("");
        if (isNull) {
            logger.warn("Collateral "+collateral+" fields are null");
            throw new IllegalFieldException(CollateralErrorMessage.FIELD_CANNOT_BE_NULL);
        }
    }

    @Override
    public void validateWorthIsPositive(float worth) {
        if (worth<0) {
            logger.warn("Collateral worth "+worth+" is not positive");
            throw new IllegalFieldException(CollateralErrorMessage.WORT_MUST_BE_POSITIVE);
        }
    }

    @Override
    public void validateCollateral(Collateral collateral) {
        validateAreFieldsNonNull(collateral);
        validateWorthIsPositive(collateral.getWorth());
    }
}
