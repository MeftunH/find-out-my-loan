package com.findoutmyloan.application.surety.mapper;

import com.findoutmyloan.application.surety.dto.SuretyDTO;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SuretyMapper {
    SuretyMapper INSTANCE=Mappers.getMapper(SuretyMapper.class);

    Surety convertToSurety(SuretySaveRequestDTO suretySaveRequestDTO);

    SuretyDTO convertToSuretyDto(Surety savedSurety);
}