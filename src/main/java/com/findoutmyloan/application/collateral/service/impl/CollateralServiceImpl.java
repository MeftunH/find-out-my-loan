package com.findoutmyloan.application.collateral.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralDTO;
import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.entity.Collateral;
import com.findoutmyloan.application.collateral.enums.CollateralWorthPercentageToBeAddToTheLoanLimit;
import com.findoutmyloan.application.collateral.mapper.CollateralMapper;
import com.findoutmyloan.application.collateral.repository.CollateralRepository;
import com.findoutmyloan.application.collateral.service.CollateralService;
import com.findoutmyloan.application.creditscore.enums.CreditScoreType;
import com.findoutmyloan.application.customer.enums.CustomerTypeAccordingToMonthlyIncome;
import com.findoutmyloan.application.customer.service.CustomerProfilerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollateralServiceImpl implements CollateralService {
    private final CollateralRepository collateralRepository;
    private final CustomerProfilerService customerProfilerService;

    @Override
    public CollateralDTO saveCollateral(CollateralSaveRequestDTO collateralSaveRequestDTO) {
        Collateral collateral=CollateralMapper.INSTANCE.convertToCollateral(collateralSaveRequestDTO);

        Collateral savedCollateral=collateralRepository.save(collateral);
        return CollateralMapper.INSTANCE.convertToCollateralDTO(savedCollateral);
    }

    public float addCollateralWorthToLoanLimit(float collateralWorth, int creditScore, float monthlyIncome, float amount) {

        switch (customerProfilerService.getCustomerProfile(creditScore, monthlyIncome)) {
            case BRONZE ->
                    amount+=collateralWorth*CollateralWorthPercentageToBeAddToTheLoanLimit.LOW_PERCENTAGE.getWorthPercentage();
            case SILVER ->
                    amount+=collateralWorth*CollateralWorthPercentageToBeAddToTheLoanLimit.MEDIUM_PERCENTAGE.getWorthPercentage();
            case GOLD ->
                    amount+=collateralWorth*CollateralWorthPercentageToBeAddToTheLoanLimit.HIGH_PERCENTAGE.getWorthPercentage();
            case PLATINUM ->
                    amount+=collateralWorth*CollateralWorthPercentageToBeAddToTheLoanLimit.VERY_HIGH_PERCENTAGE.getWorthPercentage();
        }
        return amount;
    }
//        if (C) {
//            amount=amount+collateralWorth*CollateralWorthPercentageToBeAddToTheLoanLimit.VERY_HIGH_PERCENTAGE.getWorthPercentage();
//        } else {
//            if (CreditScoreType.getCreditScoreType(creditScore)==CreditScoreType.MEDIUM_CREDIT_SCORE) {
//                if (CustomerTypeAccordingToMonthlyIncome.getCustomerTypeAccordingToMonthlyIncome(monthlyIncome)==CustomerTypeAccordingToMonthlyIncome.LOW_INCOME) {
//                    amount=amount+collateralWorthToBeAddToTheLoanLimitByMonthlyIncome(collateralWorth, CustomerTypeAccordingToMonthlyIncome.LOW_INCOME);
//                } else if (CustomerTypeAccordingToMonthlyIncome.getCustomerTypeAccordingToMonthlyIncome(monthlyIncome)==CustomerTypeAccordingToMonthlyIncome.MEDIUM_INCOME) {
//                    amount=amount+collateralWorthToBeAddToTheLoanLimitByMonthlyIncome(collateralWorth, CustomerTypeAccordingToMonthlyIncome.MEDIUM_INCOME);
//                } else if (CustomerTypeAccordingToMonthlyIncome.getCustomerTypeAccordingToMonthlyIncome(monthlyIncome)==CustomerTypeAccordingToMonthlyIncome.HIGH_INCOME) {
//                    amount=amount+collateralWorthToBeAddToTheLoanLimitByMonthlyIncome(collateralWorth, CustomerTypeAccordingToMonthlyIncome.HIGH_INCOME);
//                }
//            }
//        }
}
