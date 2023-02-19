package com.findoutmyloan.application.facade.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.findoutmyloan.application.collateral.enums.CollateralType;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.surety.enums.SuretyType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class LoanApplicationRequestDTO implements Serializable {
    @NotNull
    private String customerName;
    @NotNull
    private String customerSurname;
    @NotNull
    private long customerIdentityNo;
    @NotNull
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date customerBirthDate;
    @NotNull
    private String customerPhoneNumber;
    @NotNull
    private PersonType customerPersonType;
    @NotNull
    private float customerMonthlyIncome;
    private String suretyName;
    private String suretySurname;
    private long suretyIdentityNo;
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date suretyBirthDate;
    private String suretyPhoneNumber;
    private PersonType suretyPersonType;
    private SuretyType suretyType;
    private CollateralType collateralType;
    private float collateralWorth;
    private PaybackGuaranteeType paybackGuaranteeType;
}
