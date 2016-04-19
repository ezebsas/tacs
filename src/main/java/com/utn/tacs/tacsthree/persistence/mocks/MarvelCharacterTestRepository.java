package com.utn.tacs.tacsthree.persistence.mocks;

import java.util.ArrayList;
import java.util.List;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.TacsModel;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;

public class MarvelCharacterTestRepository implements MarvelCharacterDAO {

	public static MarvelCharacterTestRepository instance = new MarvelCharacterTestRepository();

	public static MarvelCharacterTestRepository getInstance() {
		return instance;
	}

	public List<MarvelCharacter> characters = new ArrayList<MarvelCharacter>();

	public void restart() {
		characters.clear();
		characters.add(new MarvelCharacter("1309b8799a96331925075301", "Peter Benjamin Parker",
				"Peter can cling to most surfaces, has superhuman strength..."));
		characters.add(new MarvelCharacter("1309b8799a96331925075302", "Robert Bruce Banner",
				"Dr. Bruce Banner is a genius in nuclear physics, possessing a ..."));
	}

	public MarvelCharacterTestRepository() {
		restart();
	}

	@Override
	public List<MarvelCharacter> get() {
		return characters;
	}

	@Override
	public MarvelCharacter get(TacsModel _character) throws InexistentTacsModelException {
		for (MarvelCharacter character : characters)
			if (character.sameModels(_character))
				return character;
		throw new InexistentTacsModelException("get failed");
	}

}
