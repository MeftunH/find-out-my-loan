package com.findoutmyloan.application.collateral.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralDTO;
import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.entity.Collateral;
import com.findoutmyloan.application.collateral.mapper.CollateralMapper;
import com.findoutmyloan.application.collateral.repository.CollateralRepository;
import com.findoutmyloan.application.collateral.service.CollateralService;
import com.findoutmyloan.application.customer.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollateralServiceImpl implements CollateralService {
    private final CollateralRepository collateralRepository;
    @Override
    public CollateralDTO saveCollateral(CollateralSaveRequestDTO collateralSaveRequestDTO) {
        Collateral collateral=CollateralMapper.INSTANCE.convertToCollateral(collateralSaveRequestDTO);

        Collateral savedCollateral=collateralRepository.save(collateral);
        return CollateralMapper.INSTANCE.convertToCollateralDTO(savedCollateral);

    }
}
