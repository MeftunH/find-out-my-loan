package com.findoutmyloan.application.surety.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.entity.BaseAdditionalFields;
import com.findoutmyloan.application.generic.service.BaseService;
import com.findoutmyloan.application.surety.dto.SuretyDTO;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.mapper.SuretyMapper;
import com.findoutmyloan.application.surety.repository.SuretyRepository;
import com.findoutmyloan.application.surety.service.SuretyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class SuretyServiceImpl extends BaseService<Surety> implements SuretyService {

    private final SuretyRepository suretyRepository;

    @Override
    public SuretyDTO saveSurety(SuretySaveRequestDTO suretySaveRequestDTO) {
        Surety surety=SuretyMapper.INSTANCE.convertToSurety(suretySaveRequestDTO);
        setAdditionalFields(surety);
        surety.setToCustomerId(getCurrentCustomerId());
        Surety savedSurety=suretyRepository.save(surety);
        return SuretyMapper.INSTANCE.convertToSuretyDto(savedSurety);
    }
}
