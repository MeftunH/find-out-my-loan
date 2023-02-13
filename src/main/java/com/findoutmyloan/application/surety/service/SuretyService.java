package com.findoutmyloan.application.surety.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.surety.dto.SuretyDTO;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;

public interface SuretyService {
    SuretyDTO saveSurety(SuretySaveRequestDTO suretySaveRequestDTO);
}
