package com.findoutmyloan.application.generic.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements BaseModel,Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    @Embedded
    private BaseAdditionalFields baseAdditionalFields;
}
