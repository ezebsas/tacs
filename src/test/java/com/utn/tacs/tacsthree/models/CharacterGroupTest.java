package com.utn.tacs.tacsthree.models;

import static org.junit.Assert.*;

import org.junit.Test;

import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

public class CharacterGroupTest {

	@Test
	public void createGroupRequiredValues() {
		CharacterGroup testGroup = new CharacterGroup();
		testGroup.setId("5709b8799a96331925075306");
		testGroup.setName("Test");
		testGroup.addCharacters(new MarvelCharacter());
		testGroup.valid();
	}

	@Test
	public void createUserWithoutName() {
		CharacterGroup testGroup = new CharacterGroup();
		testGroup.setId("5709b8799a96331925075306");
		testGroup.addCharacters(new MarvelCharacter());
		try {
			testGroup.valid();
		} catch (InvalidTacsModelException e) {
			assertEquals("invalid name", e.getMessage());
		}
	}

	@Test
	public void createUserWithoutCharacters() {
		CharacterGroup testGroup = new CharacterGroup();
		testGroup.setId("5709b8799a96331925075306");
		testGroup.setName("Test");
		try {
			testGroup.valid();
		} catch (InvalidTacsModelException e) {
			assertEquals("invalid characters", e.getMessage());
		}
	}
}
