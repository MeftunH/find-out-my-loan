package com.findoutmyloan.application.collateral.mapper;

import com.findoutmyloan.application.collateral.dto.CollateralDTO;
import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.entity.Collateral;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CollateralMapper {
    CollateralMapper INSTANCE = Mappers.getMapper(CollateralMapper.class);
    @Mapping(source = "baseAdditionalFieldsUpdatedDate", target = "baseAdditionalFields.updatedDate")
    @Mapping(source = "baseAdditionalFieldsCreatedDate", target = "baseAdditionalFields.createdDate")
    Collateral convertToCollateral(CollateralDTO collateralDto);
    Collateral convertToCollateral(CollateralSaveRequestDTO collateralSaveRequestDTO);
    @Mapping(source = "baseAdditionalFields.updatedDate", target = "baseAdditionalFieldsUpdatedDate")
    @Mapping(source = "baseAdditionalFields.createdDate", target = "baseAdditionalFieldsCreatedDate")
    CollateralDTO convertToCollateralDTO(Collateral collateral);

    @Mapping(source="collateralType",target="collateralType")
    @Mapping(source="collateralWorth",target="worth")
    Collateral convertLoanApplicationRequestToCollateral(LoanApplicationRequestDTO loanApplicationRequestDTO);
}