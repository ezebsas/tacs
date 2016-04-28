package com.utn.tacs.tacsthree.service;

import static com.google.common.collect.Lists.transform;

import java.util.List;

import javax.inject.Named;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.utn.tacs.tacsthree.connector.MarvelConnector;
import com.utn.tacs.tacsthree.connector.api.MarvelApiCharacter;
import com.utn.tacs.tacsthree.connector.api.MarvelImage;
import com.utn.tacs.tacsthree.connector.api.MarvelItem;
import com.utn.tacs.tacsthree.models.MarvelCharacter;

@Named
public class MarvelService {

	private static Function<MarvelItem, String> ITEM_TRANSFORM = new Function<MarvelItem, String>() {

		@Override
		public String apply(MarvelItem item) {
			return item.getName();
		}
	};

	private static Function<MarvelApiCharacter, MarvelCharacter> TRANSFORM_FUNCTION = new Function<MarvelApiCharacter, MarvelCharacter>() {

		@Override
		public MarvelCharacter apply(MarvelApiCharacter input) {
			MarvelCharacter marvelCharacter = new MarvelCharacter();
			marvelCharacter.setCharacterId(input.getId());
			marvelCharacter.setName(input.getName());
			marvelCharacter.setModified(input.getModified());
			marvelCharacter.setDescription(input.getDescription());
			marvelCharacter.setResourceURI(input.getResourceURI());

			MarvelImage thumbnail = input.getThumbnail();
			if (thumbnail != null) {
				marvelCharacter.setThumbnailUrl(thumbnail.imageUrlStandardAmazing());
			}

			marvelCharacter.setComics(Lists.transform(input.getComics().getItems(), ITEM_TRANSFORM));
			marvelCharacter.setSeries(Lists.transform(input.getSeries().getItems(), ITEM_TRANSFORM));
			marvelCharacter.setEvents(Lists.transform(input.getEvents().getItems(), ITEM_TRANSFORM));
			marvelCharacter.setStories(Lists.transform(input.getStories().getItems(), ITEM_TRANSFORM));
			
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
