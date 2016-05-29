package com.utn.tacs.tacsthree.service;

import static com.google.common.collect.Lists.transform;
import static java.lang.String.format;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.base.Function;
import com.google.inject.Inject;
import com.utn.tacs.tacsthree.connectors.MarvelConnector;
import com.utn.tacs.tacsthree.connectors.api.MarvelApiCharacter;
import com.utn.tacs.tacsthree.connectors.api.MarvelImage;
import com.utn.tacs.tacsthree.connectors.api.MarvelItem;
import com.utn.tacs.tacsthree.connectors.api.MarvelResource;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;

public class MarvelService {

	private static final Logger LOGGER = Logger.getLogger(MarvelService.class);

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
			marvelCharacter.setIdMarvel(input.getId());
			marvelCharacter.setName(input.getName());
			marvelCharacter.setModified(input.getModified());
			marvelCharacter.setDescription(input.getDescription());
			marvelCharacter.setResourceURI(input.getResourceURI());

			MarvelImage thumbnail = input.getThumbnail();
			if (thumbnail != null) {
				marvelCharacter.setThumbnailUrl(thumbnail.imageUrlStandardAmazing());
			}

			marvelCharacter.setComics(transformResource(input.getComics()));
			marvelCharacter.setSeries(transformResource(input.getSeries()));
			marvelCharacter.setEvents(transformResource(input.getEvents()));
			marvelCharacter.setStories(transformResource(input.getStories()));

			return marvelCharacter;
		}

		private List<String> transformResource(MarvelResource resource) {
			if (resource != null) {
				List<MarvelItem> items = resource.getItems();
				if (items != null) {
					return transform(items, ITEM_TRANSFORM);
				}
			}
			return null;
		}
	};

	private MarvelConnector connector;

	@Inject
	public MarvelService(MarvelConnector connector) {
		this.connector = connector;
	}

	public List<MarvelCharacter> getAllCharacters() {
		List<MarvelApiCharacter> characters = connector.getAllCharacters();

		return transform(characters, TRANSFORM_FUNCTION);
	}

	public MarvelCharacter getCharacter(Long characterId) {
		try {
			MarvelApiCharacter character = connector.getCharacter(characterId);
			return TRANSFORM_FUNCTION.apply(character);
		} catch (Exception e) {
			LOGGER.error(format("Cannot find character with id %s", characterId));
			throw new InexistentTacsModelException(e.getMessage(), e);
		}
	}

}
