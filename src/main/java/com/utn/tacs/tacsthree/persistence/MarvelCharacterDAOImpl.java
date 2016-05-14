package com.utn.tacs.tacsthree.persistence;

import static com.google.common.collect.Lists.newArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.persistence.mocks.MarvelCharacterTestRepository;
import com.utn.tacs.tacsthree.service.MarvelService;

public class MarvelCharacterDAOImpl implements MarvelCharacterDAO {

	private final MarvelService marvelService;
	private Map<Long, MarvelCharacter> characters = new HashMap<Long, MarvelCharacter>();

	@Inject
	public MarvelCharacterDAOImpl(MarvelService marvelService) {
		this.marvelService = marvelService;

		for (MarvelCharacter character : new MarvelCharacterTestRepository().get()) {
			characters.put(character.getIdMarvel(), character);
		}
	}

	@Override
	public List<MarvelCharacter> get() {
		if (characters.isEmpty()) {
			this.reload();
		}

		return newArrayList(characters.values());
	}

	@Override
	public MarvelCharacter get(MarvelCharacter _character) throws InexistentTacsModelException {
		MarvelCharacter marvelCharacter = characters.get(_character.getIdMarvel());

		if (marvelCharacter == null) {
			marvelCharacter = marvelService.getCharacter(_character.getIdMarvel());
			if (marvelCharacter == null) {
				throw new InexistentTacsModelException("get failed");
			} else {
				marvelCharacter.setId(ObjectId.get().toHexString());
				characters.put(marvelCharacter.getIdMarvel(), marvelCharacter);
			}
		}

		return marvelCharacter;
	}

	private void reload() {
		Map<Long, MarvelCharacter> characters = Maps.newHashMap();
		List<MarvelCharacter> allCharacters = marvelService.getAllCharacters();

		for (MarvelCharacter marvelCharacter : allCharacters) {
			marvelCharacter.setId(ObjectId.get().toHexString());
			characters.put(marvelCharacter.getIdMarvel(), marvelCharacter);
		}

		this.characters = characters;
	}
}
