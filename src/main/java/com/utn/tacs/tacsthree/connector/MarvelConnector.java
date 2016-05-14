package com.utn.tacs.tacsthree.connector;

import static com.utn.tacs.tacsthree.connector.MarvelUrlBuilder.MAX_LIMIT;
import static com.utn.tacs.tacsthree.connector.MarvelUrlBuilder.createCharacterUrl;
import static com.utn.tacs.tacsthree.connector.MarvelUrlBuilder.createCharactersUrl;
import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import com.google.inject.Inject;
import com.utn.tacs.tacsthree.connector.api.MarvelApiCharacter;
import com.utn.tacs.tacsthree.connector.api.MarvelApiCharacterDataContainer;
import com.utn.tacs.tacsthree.connector.api.MarvelApiCharacterDataWrapper;
import com.utn.tacs.tacsthree.connector.api.MarvelApiError;
import com.utn.tacs.tacsthree.exceptions.InexistentMarvelCharacterException;
import com.utn.tacs.tacsthree.exceptions.MarvelApiException;
import com.utn.tacs.tacsthree.jsonmapper.ObjectMapperProvider;

public class MarvelConnector {

	private static final String ERROR_CONNECTING_TO_MARVEL = "An error occurred when trying connect to Marvel";
	private static final String ERROR_CONSUMING_MARVEL_API = "An error occurred consuming Marvel API. Message %s";
	private static final String GET_REQUEST = "GET REQUEST %s";
	private static final String GET_RESPONSE = "GET RESPONSE %s STATUS %s";

	private static final Logger LOGGER = Logger.getLogger(MarvelConnector.class);

	@Inject
	public MarvelConnector() {
	}

	public MarvelApiCharacter getCharacter(Long id) {
		return get(createCharacterUrl(id)).getResults().get(0);
	}

	public List<MarvelApiCharacter> getAllCharacters() {
		Boolean first = true;
		List<MarvelApiCharacter> characters = new ArrayList<MarvelApiCharacter>();

		Long total = 0L;
		Integer offset = 0;
		while (first || offset < total) {

			try {
				if (first) {
					MarvelApiCharacterDataContainer response = get(createCharactersUrl(offset, MAX_LIMIT));
					characters.addAll(response.getResults());
					total = response.getTotal();
					first = false;
				} else {
					List<MarvelApiCharacter> list = getCharacters(MAX_LIMIT, offset);
					characters.addAll(list);
				}
			} catch (Exception e) {
				LOGGER.error(String.format("Cannot get characters with offset %s and limit %s", offset, MAX_LIMIT), e);
			}
			offset += MAX_LIMIT;
		}

		return characters;
	}

	public List<MarvelApiCharacter> getCharacters(Integer limit, Integer offset) {
		return get(createCharactersUrl(offset, limit)).getResults();

	}

	private MarvelApiCharacterDataContainer get(String url) {
		ResteasyClient client = new ResteasyClientBuilder().build().register(new ObjectMapperProvider());
		try {
			WebTarget target = client.target(url);

			LOGGER.info(format(GET_REQUEST, url));
			Response response = target.request().get(Response.class);
			LOGGER.info(format(GET_RESPONSE, url, response.getStatus()));

			if (response.getStatus() == 200) {
				return handleApiResponse(response);
			} else {
				return handleApiError(response);
			}
		} finally {
			client.close();
		}
	}

	private MarvelApiCharacterDataContainer handleApiError(Response response) {
		MarvelApiError error = response.readEntity(MarvelApiError.class);

		switch (error.getCode()) {
		case 404:
			throw new InexistentMarvelCharacterException(error.getStatus());
		case 409:
			throw new MarvelApiException(format(ERROR_CONSUMING_MARVEL_API, error.getStatus()));
		default:
			throw new MarvelApiException(ERROR_CONNECTING_TO_MARVEL);
		}

	}

	private MarvelApiCharacterDataContainer handleApiResponse(Response response) {
		MarvelApiCharacterDataWrapper dataWrapper = response.readEntity(MarvelApiCharacterDataWrapper.class);
		return dataWrapper.getData();
	}

}
