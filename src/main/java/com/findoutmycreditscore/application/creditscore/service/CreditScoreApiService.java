package com.findoutmycreditscore.application.creditscore.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.creditscore.dto.CreditScoreDTO;
import com.findoutmycreditscore.application.creditscore.dto.CreditScoreSaveRequestDTO;

public interface CreditScoreApiService {
   CreditScoreDTO getCreditScore(CreditScoreSaveRequestDTO creditScoreSaveRequestDTO);
}
