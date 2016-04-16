package com.utn.tacs.tacsthree.connector;

import static com.utn.tacs.tacsthree.connector.MarvelUrlBuilder.MAX_LIMIT;
import static com.utn.tacs.tacsthree.connector.MarvelUrlBuilder.createCharacterUrl;
import static com.utn.tacs.tacsthree.connector.MarvelUrlBuilder.createCharactersUrl;
import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import com.utn.tacs.tacsthree.connector.api.MarvelApiCharacter;
import com.utn.tacs.tacsthree.connector.api.MarvelApiCharacterDataContainer;
import com.utn.tacs.tacsthree.connector.api.MarvelApiCharacterDataWrapper;
import com.utn.tacs.tacsthree.exceptions.MarvelApiException;
import com.utn.tacs.tacsthree.jsonmapper.ObjectMapperProvider;

public class MarvelConnector {

	private static final String ERROR_LOG = "An error occurred. Error code %s, message: %s";

	ResteasyClient client = new ResteasyClientBuilder().build().register(new ObjectMapperProvider());

	public List<MarvelApiCharacter> getCharacter(Long id) {
		return get(createCharacterUrl(id)).getResults();
	}

	public List<MarvelApiCharacter> getAllCharacters() {
		List<MarvelApiCharacter> characters = new ArrayList<MarvelApiCharacter>();

		Integer offset = 0;
		List<MarvelApiCharacter> response = new ArrayList<>();

		do {
			response = getCharacters(MAX_LIMIT, offset);
			characters.addAll(response);
			offset += response.size();
		} while (MAX_LIMIT == response.size());

		return characters;
	}

	public List<MarvelApiCharacter> getCharacters(Integer limit, Integer offset) {
		return get(createCharactersUrl(offset, limit)).getResults();

	}

	private MarvelApiCharacterDataContainer get(String url) {
		WebTarget target = client.target(url);
		Response response = target.request().get(Response.class);
		MarvelApiCharacterDataWrapper dataWrapper = response.readEntity(MarvelApiCharacterDataWrapper.class);
		return handleResponse(dataWrapper);
	}

	private MarvelApiCharacterDataContainer handleResponse(MarvelApiCharacterDataWrapper dataWrapper) {
		int status = dataWrapper.getCode();

		if (200 == status) {
			return dataWrapper.getData();
		} else {
			throw new MarvelApiException(format(ERROR_LOG, status, dataWrapper.getStatus()));
		}
	}
}
