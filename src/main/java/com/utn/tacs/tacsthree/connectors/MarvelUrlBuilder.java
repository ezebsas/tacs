package com.utn.tacs.tacsthree.connectors;

public class MarvelUrlBuilder {

	private static final String AUTHENTICATION = "ts=1&apikey=b68f335ce8252c04191c54650f82e6ea&hash=5502ec70f9691242e0aa4c56a3ff7253";
	private static final String SERVICE_URL = "http://gateway.marvel.com/v1/public/";
	private static final String CHARACTERS = "characters";

	static final Integer MAX_LIMIT = 100;

	public static String createCharactersUrl(Integer offset, Integer limit) {
		StringBuilder builder = getBase();

		builder.append(CHARACTERS);
		builder.append("?").append(String.format("offset=%s&limit=%s", offset, limit));
		builder.append("&").append(AUTHENTICATION);

		return builder.toString();
	}

	public static String createCharacterUrl(Long id) {
		StringBuilder builder = getBase();

		builder.append(CHARACTERS);
		builder.append("?").append(String.format("id=%s", id));
		builder.append("&").append(AUTHENTICATION);

		return builder.toString();
	}

	private static StringBuilder getBase() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(SERVICE_URL);

		return stringBuilder;
	}

}
