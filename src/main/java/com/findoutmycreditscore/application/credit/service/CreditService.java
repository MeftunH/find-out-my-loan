package com.findoutmycreditscore.application.credit.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.credit.dto.CreditDto;
import com.findoutmycreditscore.application.credit.dto.CreditSaveRequestDTO;

public interface CreditService {
    CreditDto saveCredit(CreditSaveRequestDTO creditSaveRequestDTO);
}
