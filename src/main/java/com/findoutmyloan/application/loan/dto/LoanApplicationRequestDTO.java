package com.findoutmyloan.application.loan.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.findoutmyloan.application.collateral.CollateralType;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import com.findoutmyloan.application.person.enums.PersonType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LoanApplicationRequestDTO implements Serializable {
    @NotNull
    private long customerId;
    @NotNull
    private String customerName;
    @NotNull
    private String customerSurname;
    @NotNull
    private long customerIdentityNo;
    @NotNull
    @Past
    @JsonFormat(pattern = "dd-MM-yyyy")
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date suretyBirthDate;
    private String suretyPhoneNumber;
    private PersonType suretyPersonType;
    private CollateralType collateralType;
    private float collateralWorth;
    private long toCustomerId;
    private PaybackGuaranteeType paybackGuaranteeType;
}
