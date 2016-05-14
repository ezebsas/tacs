package com.utn.tacs.tacsthree.persistence.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;

public class MarvelCharacterTestRepository implements MarvelCharacterDAO {

	private List<MarvelCharacter> characters = new ArrayList<MarvelCharacter>();

	public MarvelCharacterTestRepository() {
		restart();
	}

	public void restart() {
		characters.clear();
		MarvelCharacter peterCharacter = new MarvelCharacter("1309b8799a96331925075301", 1009491L, "Peter Parker", "");
		peterCharacter.setModified(new Date(1315515501000L));
		peterCharacter.setResourceURI("http://gateway.marvel.com/v1/public/characters/1009491");
		peterCharacter.setThumbnailUrl(
				"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/standard_amazing.jpg");
		characters.add(peterCharacter);

		MarvelCharacter bruceCharacter = new MarvelCharacter("1309b8799a96331925075302", 1009167L, "Bruce Banner", "");
		bruceCharacter.setModified(new Date(1326594561000L));
		bruceCharacter.setThumbnailUrl(
				"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/standard_amazing.jpg");
		bruceCharacter.setResourceURI("http://gateway.marvel.com/v1/public/characters/1009167");
		characters.add(bruceCharacter);
	}

	@Override
	public List<MarvelCharacter> get() {
		return characters;
	}

	@Override
	public MarvelCharacter get(MarvelCharacter _character) throws InexistentTacsModelException {
		for (MarvelCharacter character : characters)
			if (character.sameModels(_character))
				return character;
		throw new InexistentTacsModelException("get failed");
	}

}
