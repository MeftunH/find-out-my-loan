package com.findoutmycreditscore.application.credit.mapper;

import com.findoutmycreditscore.application.credit.entity.Credit;
import com.findoutmycreditscore.application.credit.dto.CreditDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CreditMapper {
    Credit toEntity(CreditDto creditDto);

    CreditDto toDto(Credit credit);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Credit partialUpdate(CreditDto creditDto, @MappingTarget Credit credit);
}