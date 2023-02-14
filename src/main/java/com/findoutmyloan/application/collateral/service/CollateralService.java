package com.findoutmyloan.application.collateral.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralDTO;
import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;

public interface CollateralService {
    CollateralDTO saveCollateral(CollateralSaveRequestDTO collateralSaveRequestDTO);

}
