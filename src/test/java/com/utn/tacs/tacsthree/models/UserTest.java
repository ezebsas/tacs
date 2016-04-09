package com.utn.tacs.tacsthree.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void createUserRequiredValues() {
		assertTrue("Id and Name constructor working properly", new User("1", "Test").isValid());
		User testSubject = new User();
		testSubject.setId("1");
		testSubject.setName("Test");
		assertTrue("Simple working properly with required attributes", testSubject.isValid());
	}

	@Test
	public void createUserWithoutId() {
		User testSubject = new User();
		testSubject.setName("Test");
		assertFalse("Id is required", testSubject.isValid());
	}

	@Test
	public void createUserWithoutName() {
		User testSubject = new User();
		testSubject.setId("2");
		assertFalse("Name is required", testSubject.isValid());
	}

}
