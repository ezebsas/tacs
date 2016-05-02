package com.utn.tacs.tacsthree.persistence;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.newArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.service.MarvelService;

public class MarvelCharacterDAOImpl implements MarvelCharacterDAO {

	private static MarvelCharacterDAOImpl instance = new MarvelCharacterDAOImpl();

	private MarvelService marvelService = new MarvelService();
	private Map<String, MarvelCharacter> characters = new HashMap<String, MarvelCharacter>();

	public static MarvelCharacterDAOImpl getInstance() {
		return instance;
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
		MarvelCharacter marvelCharacter = characters.get(_character.getId());

		if (marvelCharacter == null) {
			throw new InexistentTacsModelException("get failed");
		}

		return marvelCharacter;
	}

	@Override
	public MarvelCharacter getByIdMarvel(MarvelCharacter _character) throws InexistentTacsModelException {
		MarvelCharacter marvelCharacter = findCharacter(_character);

		if (marvelCharacter == null) {
			marvelCharacter = marvelService.getCharacter(_character.getIdMarvel());
			if (marvelCharacter == null) {
				throw new InexistentTacsModelException("get failed");
			} else {
				marvelCharacter.setId(ObjectId.get().toHexString());
				characters.put(marvelCharacter.getId(), marvelCharacter);
			}
		}

		return marvelCharacter;
	}

	private MarvelCharacter findCharacter(MarvelCharacter _character) {
		List<MarvelCharacter> filtered = newArrayList(filter(characters.values(), new Predicate<MarvelCharacter>() {

			@Override
			public boolean apply(MarvelCharacter arg0) {
				return arg0.getIdMarvel().equals(_character.getIdMarvel());
			}
		}));

		return filtered.isEmpty() ? null : filtered.get(0);
	}

	private void reload() {
		Map<String, MarvelCharacter> characters = Maps.newHashMap();
		List<MarvelCharacter> allCharacters = marvelService.getAllCharacters();

		for (MarvelCharacter marvelCharacter : allCharacters) {
			marvelCharacter.setId(ObjectId.get().toHexString());
			characters.put(marvelCharacter.getId(), marvelCharacter);
		}

		this.characters = characters;
	}
}
