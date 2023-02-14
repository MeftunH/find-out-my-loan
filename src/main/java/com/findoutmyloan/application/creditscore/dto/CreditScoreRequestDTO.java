package com.findoutmyloan.application.creditscore.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerCreditScoreRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import com.findoutmyloan.application.surety.dto.SuretyDTO;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.enums.SuretyType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class CreditScoreRequestDTO implements Serializable {
    private long customerIdentityNo;
    private CustomerCreditScoreRequestDTO customerCreditScoreRequestDTO;
    private SuretySaveRequestDTO suretySaveRequestDTO;
    private CollateralSaveRequestDTO collateralSaveRequestDTO;
    private PaybackGuaranteeType paybackGuaranteeType;
}