package com.findoutmyloan.application.person.validation.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.person.entity.Person;
import com.findoutmyloan.application.person.entity.PersonRepository;
import com.findoutmyloan.application.person.enums.PersonErrorMessage;
import com.findoutmyloan.application.person.validation.PersonValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PersonValidationServiceImpl implements PersonValidationService {
    private final PersonRepository personRepository;

    @Override
    public void validatePhoneNumber(String phoneNumber){
        validateFieldNotNull(phoneNumber);
        if (!phoneNumber.matches("[0-9]{10}")) {
            throw new IllegalFieldException(PersonErrorMessage.PHONE_NUMBER_INVALID);
        }
    }

    private void validateFieldNotNull(String field) {
        if (Objects.equals(field, "")||field==null||field.isBlank()) {
            throw new IllegalFieldException(PersonErrorMessage.FIELD_CANNOT_BE_NULL);
        }
    }

    @Override
    public void validateIsIdentityNoUnique(Person person) {
        Optional<Person> personOptional=Optional.ofNullable(personRepository.findByIdentityNo(person.getIdentityNo()));
        Person personReturned;
        if (personOptional.isPresent()) {
            personReturned=personOptional.get();
            boolean didMatchedItself=didMatchedItself(personReturned, person);
            if (!didMatchedItself) {
                throw new IllegalFieldException(PersonErrorMessage.IDENTITY_NO_MUST_BE_UNIQUE);
            }
        }
    }

    @Override
    public void validateTurkishIdentityNo(long identityNo) {
        String strNumber=String.valueOf(identityNo);
        if (strNumber.length()!=11||strNumber.charAt(0)=='0') {
            throw new IllegalFieldException(PersonErrorMessage.IDENTITY_NO_INVALID);
        }
        int oddSum=0, evenSum=0, controlDigit=0;
        for (int i=0; i<=8; i++) {
            if (i%2==0) {
                oddSum+=Character.getNumericValue(strNumber.charAt(i));

            } else {
                evenSum+=Character.getNumericValue(strNumber.charAt(i));
            }
        }
        controlDigit=(oddSum*7-evenSum)%10;
        if (Character.getNumericValue(strNumber.charAt(9))!=controlDigit) {
            throw new IllegalFieldException(PersonErrorMessage.IDENTITY_NO_INVALID);
        }
        if (Character.getNumericValue(strNumber.charAt(10))!=(controlDigit+evenSum+oddSum)%10) {
            throw new IllegalFieldException(PersonErrorMessage.IDENTITY_NO_INVALID);
        }
    }

    @Override
    public void validateIsPhoneNoUnique(Person person) {
        Optional<Person> personOptional=Optional.ofNullable(personRepository.findByPhoneNumber(person.getPhoneNumber()));
        Person personReturned;
        if (personOptional.isPresent()) {
            personReturned=personOptional.get();
            boolean didMatchedItself=didMatchedItself(personReturned, person);
            if (!didMatchedItself) {
                throw new IllegalFieldException(PersonErrorMessage.PHONE_NUMBER_MUST_BE_UNIQUE);
            }
        }
    }

    private Boolean didMatchedItself(Person usrUserReturned, Person person) {
        return usrUserReturned.getId().equals(person.getId());
    }
}
