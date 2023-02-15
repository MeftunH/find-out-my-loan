package com.findoutmyloan.application.notification.service.impl;

import com.findoutmyloan.application.generic.service.BaseService;
import com.findoutmyloan.application.notification.dto.SmsDto;
import com.findoutmyloan.application.notification.entity.Sms;
import com.findoutmyloan.application.notification.mapper.SmsMapper;
import com.findoutmyloan.application.notification.repository.SmsRepository;
import com.findoutmyloan.application.notification.service.NotificationService;
import com.findoutmyloan.application.person.entity.Person;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */
@Service("sms")
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class SmsNotificationService extends BaseService<Sms> implements NotificationService {
    private final SmsRepository smsRepository;
    private static final Logger log = LoggerFactory.getLogger(SmsNotificationService.class);
    @Override
    public void notify(Person person, String message) {

        SmsDto smsDto = SmsDto.builder()
                .message(message)
                .phoneNumber(person.getPhoneNumber())
                .build();
        Sms sms=SmsMapper.INSTANCE.toEntity(smsDto);
        setAdditionalFields(sms);
        smsRepository.save(sms);
        log.info("SMS notification sent to " + person.getPhoneNumber() + " with message: " + message);
    }
}
