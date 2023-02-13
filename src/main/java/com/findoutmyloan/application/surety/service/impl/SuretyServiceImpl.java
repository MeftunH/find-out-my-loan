package com.findoutmyloan.application.surety.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.surety.dto.SuretyDTO;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.mapper.SuretyMapper;
import com.findoutmyloan.application.surety.repository.SuretyRepository;
import com.findoutmyloan.application.surety.service.SuretyService;
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
