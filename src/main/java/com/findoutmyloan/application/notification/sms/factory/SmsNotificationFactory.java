package com.findoutmyloan.application.notification.sms.factory;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.generic.service.BaseService;
import com.findoutmyloan.application.notification.entity.Notification;
import com.findoutmyloan.application.notification.enums.NotificationErrorMessage;
import com.findoutmyloan.application.notification.enums.NotificationType;
import com.findoutmyloan.application.notification.factory.NotificationFactory;
import com.findoutmyloan.application.notification.sms.dto.SmsDto;
import com.findoutmyloan.application.notification.sms.entity.Sms;
import com.findoutmyloan.application.notification.sms.mapper.SmsMapper;
import com.findoutmyloan.application.notification.sms.repository.SmsRepository;
import com.findoutmyloan.application.person.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class SmsNotificationFactory extends BaseService<Sms> implements NotificationFactory {
    private final SmsRepository smsRepository;

    @Override
    public Notification createNotification(NotificationType notificationType, String message, Person recipient) {
        if (notificationType.equals(NotificationType.SMS)) {
            SmsDto smsDto = SmsDto.builder()
                    .message(message)
                    .phoneNumber(recipient.getPhoneNumber())
                    .build();
            Sms sms=SmsMapper.INSTANCE.toEntity(smsDto);
            setAdditionalFields(sms);
            smsRepository.save(sms);
            return sms;
        } else {
            throw new GeneralBusinessException(NotificationErrorMessage.NOTIFICATION_TYPE_NOT_SUPPORTED);
        }
    }
}
