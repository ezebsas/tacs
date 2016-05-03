package com.utn.tacs.tacsthree.api.v1.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;
import com.utn.tacs.tacsthree.persistence.mocks.MarvelCharacterTestRepository;

public class MarvelCharactersControllerTest {

	private MarvelCharacterDAO repository;
	private MarvelCharactersController controller;

	@Before
	public void setUp() throws Exception {
		repository = new MarvelCharacterTestRepository();
		controller = new MarvelCharactersController(repository);
	}

	@Test
	public void getAllCharacters() {
		assertEquals(repository.get().size(), controller.getAllCharacters().size());
	}

	@Test
	public void getCharacter() {
		Long _id = repository.get().get(0).getIdMarvel();
		MarvelCharacter _character = controller.getCharacter(_id);
		assertEquals(_id, _character.getIdMarvel());
	}

	@Test
	public void getCharacterInexistentCharacter() {
		try {
			controller.getCharacter(1309L);
		} catch (InexistentTacsModelException e) {
			assertEquals("get failed", e.getMessage());
		}

	}

}
