package com.findoutmyloan.application.creditscore.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.creditscore.dto.CreditScoreResponseDTO;
import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;

public interface CreditScoreApiService {
   CreditScoreResponseDTO getCreditScore(CreditScoreRequestDTO creditScoreRequestDTO);
}
