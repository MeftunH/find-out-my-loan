package com.findoutmyloan.application.creditscore.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerCreditScoreRequestDTO;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CreditScoreRequestDTO implements Serializable {
    private long customerIdentityNo;
    private CustomerCreditScoreRequestDTO customerCreditScoreRequestDTO;
    private SuretySaveRequestDTO suretySaveRequestDTO;
    private CollateralSaveRequestDTO collateralSaveRequestDTO;
    private PaybackGuaranteeType paybackGuaranteeType;
}