package com.findoutmyloan.application.collateral.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.calculationStrategy.CollateralCalculationStrategy;
import com.findoutmyloan.application.collateral.calculationStrategy.impl.*;
import com.findoutmyloan.application.collateral.dto.CollateralDTO;
import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.entity.Collateral;
import com.findoutmyloan.application.collateral.mapper.CollateralMapper;
import com.findoutmyloan.application.collateral.repository.CollateralRepository;
import com.findoutmyloan.application.collateral.service.CollateralService;
import com.findoutmyloan.application.collateral.validation.service.CollateralValidationService;
import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.customer.profiler.service.CustomerProfilerService;
import com.findoutmyloan.application.generic.service.BaseService;
import com.findoutmyloan.application.log.SingletonLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class CollateralServiceImpl extends BaseService<Collateral> implements CollateralService {
    private final SingletonLogger logger=SingletonLogger.getInstance();
    private final CollateralRepository collateralRepository;
    private final CustomerProfilerService customerProfilerService;
    private final CollateralValidationService collateralValidationService;

    @Override
    public CollateralDTO saveCollateral(CollateralSaveRequestDTO collateralSaveRequestDTO) {
        Collateral collateral=CollateralMapper.INSTANCE.convertToCollateral(collateralSaveRequestDTO);
        setAdditionalFields(collateral);

        collateralValidationService.validateCollateral(collateral);

        collateral.setCustomerId(getCurrentCustomerId());
        Collateral savedCollateral=collateralRepository.save(collateral);
        logger.info("Collateral "+collateral+" saved successfully");
        return CollateralMapper.INSTANCE.convertToCollateralDTO(savedCollateral);
    }

    public float addCollateralWorthToLoanLimit(float collateralWorth, int creditScore, float monthlyIncome, float amount) {

        CustomerProfiler customerProfile=customerProfilerService.getCustomerProfile(creditScore, monthlyIncome);
        CollateralCalculationStrategy strategy;

        switch (customerProfile) {
            case BRONZE -> strategy=new BronzeCollateralCalculationStrategy();
            case SILVER -> strategy=new SilverCollateralCalculationStrategy();
            case GOLD -> strategy=new GoldCollateralCalculationStrategy();
            case PLATINUM -> strategy=new PlatinumCollateralCalculationStrategy();
            default -> strategy=new WoodCollateralCalculationStrategy();
        }
        amount=strategy.calculateLoanAmount(collateralWorth, amount);
        return amount;
    }
}
