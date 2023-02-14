package com.findoutmyloan.application.facade;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerCreditScoreRequestDTO;
import com.findoutmyloan.application.loan.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;

import java.util.List;
import java.util.Objects;

public interface EntityFacade {
    void saveEntity(SuretySaveRequestDTO suretySaveRequestDTO, CollateralSaveRequestDTO collateralSaveRequestDTO);
}
