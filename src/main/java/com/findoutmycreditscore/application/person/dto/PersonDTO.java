package com.findoutmycreditscore.application.person.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.person.enums.PersonType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
public class PersonDTO implements Serializable {
    private String name;
    private String surname;
    private long identityNo;
    private Date birthDate;
    private String phoneNumber;
    private PersonType personType;

}
