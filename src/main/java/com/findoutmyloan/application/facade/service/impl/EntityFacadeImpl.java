package com.findoutmyloan.application.facade.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.service.CollateralService;
import com.findoutmyloan.application.customer.validation.CustomerValidationService;
import com.findoutmyloan.application.facade.service.EntityFacade;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.service.SuretyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class EntityFacadeImpl implements EntityFacade {
    private final SuretyService suretyService;
    private final CollateralService collateralService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerValidationService.class);

    @Override
    public void saveEntity(SuretySaveRequestDTO suretySaveRequestDTO, CollateralSaveRequestDTO collateralSaveRequestDTO) {
        if (suretySaveRequestDTO.getPersonType()!=null) {
            suretyService.saveSurety(suretySaveRequestDTO);
            logger.info("Loan Application Surety saved");
        }

        if (collateralSaveRequestDTO.getCollateralType()!=null) {
            collateralService.saveCollateral(collateralSaveRequestDTO);
            logger.info("Loan Application Collateral saved");
        }
    }
}
