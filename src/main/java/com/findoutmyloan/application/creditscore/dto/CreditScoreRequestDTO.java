package com.findoutmyloan.application.creditscore.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CreditScoreRequestDTO {
    @NotNull
    private String customerName;
    @NotNull
    private String customerSurname;
    @NotNull
    private String customerPhoneNumber;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private Date customerBirthDate;

    private float collateral;

    private long suretyIdentityNumber;
}