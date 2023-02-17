package com.findoutmyloan.application.person.validation.impl;

import com.findoutmyloan.application.person.entity.Person;
import java.security.GeneralSecurityException;
import org.junit.jupiter.api.*;

public class PersonValidationServiceImplSymflowerTest {
	@Test
	public void validateIsIdentityNoUnique1() {
		PersonValidationServiceImpl p = null; // TODO This is a fallback value due to incomplete analysis.
		Person person = null; // TODO This is a fallback value due to incomplete analysis.
		p.validateIsIdentityNoUnique(person);
	}

	@Test
	public void validateIsPhoneNoUnique2() {
		PersonValidationServiceImpl p = null; // TODO This is a fallback value due to incomplete analysis.
		Person person = null; // TODO This is a fallback value due to incomplete analysis.
		p.validateIsPhoneNoUnique(person);
	}

	@Test
	public void validatePhoneNumber3() throws GeneralSecurityException {
		PersonValidationServiceImpl p = null; // TODO This is a fallback value due to incomplete analysis.
		String phoneNumber = null; // TODO This is a fallback value due to incomplete analysis.
		p.validatePhoneNumber(phoneNumber);
	}

	@Test
	public void validateTurkishIdentityNo4() {
		PersonValidationServiceImpl p = null; // TODO This is a fallback value due to incomplete analysis.
		long identityNo = 0L; // TODO This is a fallback value due to incomplete analysis.
		p.validateTurkishIdentityNo(identityNo);
	}
}
