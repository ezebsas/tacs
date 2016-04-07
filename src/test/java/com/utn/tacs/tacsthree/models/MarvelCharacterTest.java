package com.utn.tacs.tacsthree.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class MarvelCharacterTest {

	@Test
	public void createCharacterRequiredValues() {
		MarvelCharacter testSpidey = new MarvelCharacter();
		testSpidey.setId("1");
		assertTrue("Required attributes working properly", testSpidey.isValid());
	}

	@Test
	public void createCharacterWithoutId() {
		MarvelCharacter testSpidey = new MarvelCharacter();
		assertFalse("Id is required", testSpidey.isValid());
	}

}
