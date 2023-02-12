package com.findoutmycreditscore.application.credit.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.credit.dto.CreditDto;
import com.findoutmycreditscore.application.credit.dto.CreditSaveRequestDTO;
import com.findoutmycreditscore.application.credit.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    @Override
    public CreditDto saveCredit(CreditSaveRequestDTO creditSaveRequestDTO) {
        return null;
    }
}
