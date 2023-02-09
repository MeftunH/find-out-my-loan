package com.findoutmycreditscore.application.person.model;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.person.enums.PersonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "person")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "person_id_seq")
    @GeneratedValue(generator="personSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "identity_no", nullable = false)
    private long identityNo;

    @Column(name = "birth_date", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "person_type", length = 30, nullable = false)
    private PersonType personType;


}
