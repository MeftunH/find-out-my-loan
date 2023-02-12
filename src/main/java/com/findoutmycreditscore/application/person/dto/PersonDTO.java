package com.findoutmycreditscore.application.person.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.person.enums.PersonType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.aspectj.lang.annotation.After;

import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
public class PersonDTO implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private long identityNo;
    @NotNull
    @Past
    private Date birthDate;
    @NotNull
    private String phoneNumber;
    @NotNull
    private PersonType personType;

}
