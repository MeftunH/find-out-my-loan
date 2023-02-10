package com.findoutmycreditscore.application.generic.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {
    @Column(name = "created_date", nullable = false,updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "updated_date", nullable = false)
    @LastModifiedDate
    private Date updatedDate;

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private Long createdBy;
    @Column(name = "updated_by")
    @LastModifiedBy
    private Long updatedBy;
}