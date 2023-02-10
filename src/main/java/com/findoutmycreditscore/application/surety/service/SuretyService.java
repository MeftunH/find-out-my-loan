package com.findoutmycreditscore.application.surety.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.surety.dto.SuretyDTO;
import com.findoutmycreditscore.application.surety.dto.SuretySaveRequestDTO;

public interface SuretyService {
    SuretyDTO saveSurety(SuretySaveRequestDTO suretySaveRequestDTO);
}
