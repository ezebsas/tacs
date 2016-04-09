package com.utn.tacs.tacsthree.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class CharacterGroupTest {

	@Test
	public void createGroupRequiredValues() {
		CharacterGroup testGroup = new CharacterGroup();
		testGroup.setId("1");
		testGroup.setName("Test");
		testGroup.addCharacters(new MarvelCharacter());
		assertTrue("Simple working properly with required attributes", testGroup.isValid());
	}

	@Test
	public void createUserWithoutId() {
		CharacterGroup testGroup = new CharacterGroup();
		testGroup.setName("Test");
		testGroup.addCharacters(new MarvelCharacter());
		assertFalse("Id is required", testGroup.isValid());
	}

	@Test
	public void createUserWithoutName() {
		CharacterGroup testGroup = new CharacterGroup();
		testGroup.setId("2");
		testGroup.addCharacters(new MarvelCharacter());
		assertFalse("Name is required", testGroup.isValid());
	}

	@Test
	public void createUserWithoutCharacters() {
		CharacterGroup testGroup = new CharacterGroup();
		testGroup.setId("2");
		testGroup.setName("Test");
		assertFalse("The group must have at least one character", testGroup.isValid());
	}

}
