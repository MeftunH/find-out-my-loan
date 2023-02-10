package com.findoutmycreditscore.application.surety.mapper;

import com.findoutmycreditscore.application.surety.dto.SuretyDTO;
import com.findoutmycreditscore.application.surety.entity.Surety;
import com.findoutmycreditscore.application.surety.dto.SuretySaveRequestDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SuretyMapper {
    SuretyMapper INSTANCE=Mappers.getMapper(SuretyMapper.class);

    Surety convertToSurety(SuretySaveRequestDTO suretySaveRequestDTO);

    SuretyDTO convertToSuretyDto(Surety savedSurety);
}