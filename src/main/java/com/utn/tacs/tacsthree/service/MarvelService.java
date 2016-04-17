package com.utn.tacs.tacsthree.service;

import static com.google.common.collect.Lists.transform;

import java.util.List;

import com.google.common.base.Function;
import com.utn.tacs.tacsthree.connector.MarvelConnector;
import com.utn.tacs.tacsthree.connector.api.MarvelApiCharacter;
import com.utn.tacs.tacsthree.connector.api.MarvelImage;
import com.utn.tacs.tacsthree.models.MarvelCharacter;

public class MarvelService {

	private static Function<MarvelApiCharacter, MarvelCharacter> TRANSFORM_FUNCTION = new Function<MarvelApiCharacter, MarvelCharacter>() {

		@Override
		public MarvelCharacter apply(MarvelApiCharacter input) {
			MarvelCharacter marvelCharacter = new MarvelCharacter();
			marvelCharacter.setCharacterId(input.getId());
			marvelCharacter.setName(input.getName());
			marvelCharacter.setDescription(input.getDescription());
			marvelCharacter.setResourceURI(input.getResourceURI());

			MarvelImage thumbnail = input.getThumbnail();
			if (thumbnail != null) {
				marvelCharacter.setThumbnailUrl(thumbnail.imageUrlStandardAmazing());
			}

			return marvelCharacter;
		}
	};

	private MarvelConnector connector = new MarvelConnector();

	public List<MarvelCharacter> getAllCharacters() {
		List<MarvelApiCharacter> characters = connector.getAllCharacters();

		return transform(characters, TRANSFORM_FUNCTION);
	}

	public MarvelCharacter getCharacter(Long characterId) {
		MarvelApiCharacter character = connector.getCharacter(characterId);

		return TRANSFORM_FUNCTION.apply(character);
	}

	public void setConnector(MarvelConnector connector) {
		this.connector = connector;
	}

}
