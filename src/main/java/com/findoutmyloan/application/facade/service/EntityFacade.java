package com.findoutmyloan.application.facade.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
public interface EntityFacade {
    void saveEntity(SuretySaveRequestDTO suretySaveRequestDTO, CollateralSaveRequestDTO collateralSaveRequestDTO);

}
