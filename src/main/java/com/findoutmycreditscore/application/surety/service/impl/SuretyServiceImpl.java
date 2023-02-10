package com.findoutmycreditscore.application.surety.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.surety.dto.SuretyDTO;
import com.findoutmycreditscore.application.surety.entity.Surety;
import com.findoutmycreditscore.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmycreditscore.application.surety.mapper.SuretyMapper;
import com.findoutmycreditscore.application.surety.repository.SuretyRepository;
import com.findoutmycreditscore.application.surety.service.SuretyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuretyServiceImpl implements SuretyService {

    private final SuretyRepository suretyRepository;
    @Override
    public SuretyDTO saveSurety(SuretySaveRequestDTO suretySaveRequestDTO) {
        Surety surety = SuretyMapper.INSTANCE.convertToSurety(suretySaveRequestDTO);
        Surety savedSurety = suretyRepository.save(surety);
        return SuretyMapper.INSTANCE.convertToSuretyDto(savedSurety);
    }
}
