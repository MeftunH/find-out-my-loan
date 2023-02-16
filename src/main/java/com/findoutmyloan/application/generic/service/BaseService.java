package com.findoutmyloan.application.generic.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.entity.BaseAdditionalFields;
import com.findoutmyloan.application.generic.entity.BaseEntity;
import com.findoutmyloan.application.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public abstract class BaseService<E extends BaseEntity> {
    private AuthenticationService authenticationService;
    @Autowired
    public void setAuthenticationService(@Lazy AuthenticationService authenticationService) {
        this.authenticationService=authenticationService;
    }
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
    public Long getCurrentCustomerId() {
        return authenticationService.getCurrentCustomer().getId();
    }
}
