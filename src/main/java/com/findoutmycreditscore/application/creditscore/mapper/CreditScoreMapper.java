package com.findoutmycreditscore.application.creditscore.mapper;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.creditscore.dto.CreditScoreDTO;
import com.findoutmycreditscore.application.creditscore.dto.CreditScoreSaveRequestDTO;
import com.findoutmycreditscore.application.creditscore.entity.CreditScore;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE ,componentModel = "spring")
public interface CreditScoreMapper {
    CreditScoreMapper INSTANCE=Mappers.getMapper(CreditScoreMapper.class);

    CreditScore convertToCreditScore(CreditScoreSaveRequestDTO creditScoreSaveRequestDTO);

    CreditScoreDTO convertToCreditScoreDTO(CreditScore creditScore);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)CreditScore partialUpdate(CreditScoreSaveRequestDTO creditScoreSaveRequestDTO, @MappingTarget CreditScore creditScore);
    CreditScore partialUpdate(CreditScoreDTO creditScoreDto, @MappingTarget CreditScore creditScore);
}
