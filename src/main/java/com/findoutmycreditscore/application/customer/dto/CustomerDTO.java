package com.findoutmycreditscore.application.customer.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.customer.enums.PaybackGuaranteeType;
import com.findoutmycreditscore.application.person.dto.PersonDTO;
import com.findoutmycreditscore.application.person.enums.PersonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
public class CustomerDTO extends PersonDTO implements Serializable{
    private final float monthlyIncome;
    private final PaybackGuaranteeType paybackGuaranteeType;
}
