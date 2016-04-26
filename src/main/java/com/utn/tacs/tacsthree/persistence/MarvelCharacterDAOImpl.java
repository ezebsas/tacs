package com.utn.tacs.tacsthree.persistence;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;

import com.google.common.collect.Lists;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.TacsModel;
import com.utn.tacs.tacsthree.service.MarvelService;

public class MarvelCharacterDAOImpl implements MarvelCharacterDAO {

	private MarvelService marvelService = new MarvelService();
	private Map<String, MarvelCharacter> characters;

	@Override
	public List<MarvelCharacter> get() {
		characters = marvelService.getAllCharacters().stream()
				.collect(Collectors.toMap(v -> ObjectId.get().toHexString(), v -> v));
		return newArrayList(characters.values());
	}

	@Override
	public MarvelCharacter get(TacsModel _character) throws InexistentTacsModelException {
		if (characters == null) {
			this.get();
		}
		MarvelCharacter marvelCharacter = characters.get(_character.getId());

		if (marvelCharacter != null) {
			return marvelCharacter;
		} else {
			throw new InexistentTacsModelException("get failed");
		}
	}

}
