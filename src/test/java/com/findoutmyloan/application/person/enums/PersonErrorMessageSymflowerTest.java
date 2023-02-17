package com.findoutmyloan.application.person.enums;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PersonErrorMessageSymflowerTest {
	@Test
	public void getDetailMessage1() {
		PersonErrorMessage p = PersonErrorMessage.IDENTITY_NO_MUST_BE_UNIQUE;
		String expected = "Please check the identity no of the customer.";
		String actual = p.getDetailMessage();

		assertEquals(expected, actual);
	}

	@Test
	public void getMessage2() {
		PersonErrorMessage p = PersonErrorMessage.IDENTITY_NO_MUST_BE_UNIQUE;
		String expected = "Identity No Must Be Unique!";
		String actual = p.getMessage();

		assertEquals(expected, actual);
	}
}
