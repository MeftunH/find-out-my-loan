package com.findoutmyloan.application.security.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import lombok.Data;

@Data
public class SecurityLoginRequestDTO {
    private Long identityNo;
    private String password;
}
