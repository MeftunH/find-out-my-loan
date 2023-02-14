package com.findoutmyloan.application.collateral.mapper;

import com.findoutmyloan.application.collateral.dto.CollateralDTO;
import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.entity.Collateral;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CollateralMapper {
    CollateralMapper INSTANCE = Mappers.getMapper(CollateralMapper.class);
    Collateral convertToCollateral(CollateralDTO collateralDto);
    Collateral convertToCollateral(CollateralSaveRequestDTO collateralSaveRequestDTO);
    CollateralDTO convertToCollateralDTO(Collateral collateral);
}