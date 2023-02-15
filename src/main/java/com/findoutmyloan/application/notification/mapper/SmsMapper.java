package com.findoutmyloan.application.notification.mapper;

import com.findoutmyloan.application.notification.entity.Sms;
import com.findoutmyloan.application.notification.dto.SmsDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SmsMapper {
    SmsMapper INSTANCE=Mappers.getMapper(SmsMapper.class);

    @Mapping(source = "baseAdditionalFieldsUpdatedDate", target = "baseAdditionalFields.updatedDate")
    @Mapping(source = "baseAdditionalFieldsCreatedDate", target = "baseAdditionalFields.createdDate")
    Sms toEntity(SmsDto smsDto);

    @InheritInverseConfiguration(name = "toEntity")
    SmsDto toDto(Sms sms);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Sms partialUpdate(SmsDto smsDto, @MappingTarget Sms sms);
}