package com.findoutmyloan.application.generic.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.entity.BaseAdditionalFields;
import com.findoutmyloan.application.generic.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public abstract class BaseService<E extends BaseEntity> {
    public void setAdditionalFields(E entity) {
        BaseAdditionalFields baseAdditionalFields=entity.getBaseAdditionalFields();
        if (baseAdditionalFields==null) {
            baseAdditionalFields=new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }
        if (entity.getId()==null) {
            baseAdditionalFields.setCreatedDate(new Date());
        }
        baseAdditionalFields.setUpdatedDate(new Date());
    }
}
