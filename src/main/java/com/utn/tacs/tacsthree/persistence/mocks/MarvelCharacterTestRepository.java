package com.utn.tacs.tacsthree.persistence.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;

public class MarvelCharacterTestRepository implements MarvelCharacterDAO {

	public List<MarvelCharacter> characters = new ArrayList<MarvelCharacter>();

	public MarvelCharacterTestRepository() {
		characters.add(new MarvelCharacter("1309b8799a96331925075301", "Peter Benjamin Parker",
				"Peter can cling to most surfaces, has superhuman strength..."));
		characters.add(new MarvelCharacter("1309b8799a96331925075302", "Robert Bruce Banner",
				"Dr. Bruce Banner is a genius in nuclear physics, possessing a ..."));
	}

	@Override
	public List<MarvelCharacter> get() {
		return characters;
	}

	@Override
	public MarvelCharacter get(MarvelCharacter _character) throws InexistentTacsModelException {
		try {
			return characters.stream().filter(o -> o.getId().equals(_character.getId())).findFirst().get();
		} catch (NoSuchElementException e) {
			throw new InexistentTacsModelException("get failed");
		}
	}

}
