package com.findoutmyloan.application.notification.mapper;

import com.findoutmyloan.application.notification.dto.NotificationDto;
import com.findoutmyloan.application.notification.entity.Notification;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);
    @Mapping(source = "baseAdditionalFieldsUpdatedDate", target = "baseAdditionalFields.updatedDate")
    @Mapping(source = "baseAdditionalFieldsCreatedDate", target = "baseAdditionalFields.createdDate")
    Notification convertToNotification(NotificationDto notificationDto);

    NotificationDto convertToNotificationDTO(Notification notification);
}