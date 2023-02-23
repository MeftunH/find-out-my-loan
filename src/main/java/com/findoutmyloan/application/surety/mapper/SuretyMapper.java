package com.findoutmyloan.application.surety.mapper;

import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.surety.dto.SuretyDTO;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SuretyMapper {
    SuretyMapper INSTANCE=Mappers.getMapper(SuretyMapper.class);

    Surety convertToSurety(SuretySaveRequestDTO suretySaveRequestDTO);

    @Mapping(source = "baseAdditionalFields.updatedDate", target = "baseAdditionalFieldsUpdatedDate")
    @Mapping(source = "baseAdditionalFields.createdDate", target = "baseAdditionalFieldsCreatedDate")
    SuretyDTO convertToSuretyDto(Surety savedSurety);

    @Mapping(source="suretyName",target="name")
    @Mapping(source="suretySurname",target="surname")
    @Mapping(source="suretyIdentityNo",target="identityNo")
    @Mapping(source="suretyBirthDate",target="birthDate")
    @Mapping(source="suretyPhoneNumber",target="phoneNumber")
    @Mapping(source="suretyPersonType",target="personType")
    Surety convertLoanApplicationRequestToSurety(LoanApplicationRequestDTO loanApplicationRequestDTO);
}