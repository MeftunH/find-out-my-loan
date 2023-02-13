package com.findoutmyloan.application.person.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.findoutmyloan.application.person.enums.PersonType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    @NotNull
    private String phoneNumber;
    @NotNull
    private PersonType personType;

}
