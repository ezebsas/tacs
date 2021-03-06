package com.utn.tacs.tacsthree.api.v1.controllers;

import java.util.List;

import com.google.inject.Inject;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;

public class MarvelCharactersController {

	private MarvelCharacterDAO repository;

	@Inject
	public MarvelCharactersController(MarvelCharacterDAO _repository) {
		this.repository = _repository;
	}

	public List<MarvelCharacter> getAllCharacters() {
		return repository.get();
	}

	public MarvelCharacter getCharacter(Long id) throws InexistentTacsModelException {
		return repository.get(new MarvelCharacter(id));
	}

}
