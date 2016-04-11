package com.utn.tacs.tacsthree.models;

import static org.junit.Assert.*;

import org.junit.Test;

import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

public class UserTest {

	@Test
	public void createUserRequiredValues() {
		new User("1", "Test").valid();
		User testSubject = new User();
		testSubject.setId("1");
		testSubject.setName("Test");
		testSubject.valid();
	}

	@Test
	public void createUserWithoutId() {
		User testSubject = new User();
		testSubject.setName("Test");
		try {
			testSubject.valid();
		} catch (InvalidTacsModelException e) {
			assertEquals("invalid id", e.getMessage());
		}
	}

	@Test
	public void createUserWithoutName() {
		User testSubject = new User();
		testSubject.setId("2");
		try {
			testSubject.valid();
		} catch (InvalidTacsModelException e) {
			assertEquals("invalid name", e.getMessage());
		}
	}
}
