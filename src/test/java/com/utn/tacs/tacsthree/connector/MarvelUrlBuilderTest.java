package com.utn.tacs.tacsthree.connector;

import static com.utn.tacs.tacsthree.connectors.MarvelUrlBuilder.createCharacterUrl;
import static com.utn.tacs.tacsthree.connectors.MarvelUrlBuilder.createCharactersUrl;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MarvelUrlBuilderTest {

	@Test
	public void buildCharactersUrl() {
		String expected = "http://gateway.marvel.com/v1/public/characters?offset=20&limit=10&ts=1&apikey=b68f335ce8252c04191c54650f82e6ea&hash=5502ec70f9691242e0aa4c56a3ff7253";
		String url = createCharactersUrl(20, 10);

		assertEquals(expected, url);
	}

	@Test
	public void buildCharacterUrl() {
		String expected = "http://gateway.marvel.com/v1/public/characters?id=100&ts=1&apikey=b68f335ce8252c04191c54650f82e6ea&hash=5502ec70f9691242e0aa4c56a3ff7253";
		String url = createCharacterUrl(100L);

		assertEquals(expected, url);
	}
}
