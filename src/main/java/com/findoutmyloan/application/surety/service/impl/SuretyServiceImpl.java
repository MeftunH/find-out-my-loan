package com.findoutmyloan.application.surety.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.service.BaseService;
import com.findoutmyloan.application.notification.sms.observer.SmsNotificationObserver;
import com.findoutmyloan.application.surety.dto.SuretyDTO;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.mapper.SuretyMapper;
import com.findoutmyloan.application.surety.repository.SuretyRepository;
import com.findoutmyloan.application.surety.service.SuretyService;
import com.findoutmyloan.application.surety.validation.service.SuretyValidationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class SuretyServiceImpl extends BaseService<Surety> implements SuretyService {

    private final SuretyRepository suretyRepository;
    private final SuretyValidationService suretyValidationService;
    private static final Logger logger = LoggerFactory.getLogger(SuretyValidationService.class);

    @Override
    public SuretyDTO saveSurety(SuretySaveRequestDTO suretySaveRequestDTO) {
        Surety surety=SuretyMapper.INSTANCE.convertToSurety(suretySaveRequestDTO);
        setAdditionalFields(surety);
        suretyValidationService.validateSurety(surety);
        logger.info("Surety is validated");
        surety.setToCustomerId(getCurrentCustomerId());
        Surety savedSurety=suretyRepository.save(surety);
        logger.info("Surety {} saved successfully",surety);
        return SuretyMapper.INSTANCE.convertToSuretyDto(savedSurety);
    }
}
