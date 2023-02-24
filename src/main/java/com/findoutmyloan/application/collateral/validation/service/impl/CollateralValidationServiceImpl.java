package com.findoutmyloan.application.collateral.validation.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */


import com.findoutmyloan.application.collateral.entity.Collateral;
import com.findoutmyloan.application.collateral.enums.CollateralErrorMessage;
import com.findoutmyloan.application.collateral.service.impl.CollateralServiceImpl;
import com.findoutmyloan.application.collateral.validation.service.CollateralValidationService;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollateralValidationServiceImpl implements CollateralValidationService {
    private static final Logger logger = LoggerFactory.getLogger(CollateralValidationServiceImpl.class);

    @Override
    public void validateAreFieldsNonNull(Collateral collateral) {
        boolean isNull = collateral.getCollateralType() == null || String.valueOf(collateral.getWorth()).equals("");
        if (isNull) {
            logger.warn("Collateral {} fields are null",collateral);
            throw new IllegalFieldException(CollateralErrorMessage.FIELD_CANNOT_BE_NULL);
        }
    }

    @Override
    public void validateWorthIsPositive(float worth) {
      if (worth < 0) {
          logger.warn("Collateral worth {} is not positive",worth);
          throw new IllegalFieldException(CollateralErrorMessage.WORT_MUST_BE_POSITIVE);
      }
    }

    @Override
    public void validateCollateral(Collateral collateral) {
        validateAreFieldsNonNull(collateral);
        validateWorthIsPositive(collateral.getWorth());
    }
}
