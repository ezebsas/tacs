package com.utn.tacs.tacsthree.models;

import static org.junit.Assert.*;

import org.junit.Test;

import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

public class MarvelCharacterTest {

	@Test
	public void createCharacterRequiredValues() {
		MarvelCharacter testSpidey = new MarvelCharacter();
		testSpidey.setId("5709b8799a96331925075306");
		testSpidey.valid();
	}

	@Test
	public void createCharacterWithoutId() {
		MarvelCharacter testSpidey = new MarvelCharacter();
		try {
			testSpidey.valid();
		} catch (InvalidTacsModelException e) {
			assertEquals("invalid id", e.getMessage());
		}
	}
}
