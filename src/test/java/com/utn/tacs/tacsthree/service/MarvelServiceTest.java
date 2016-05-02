package com.utn.tacs.tacsthree.service;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.utn.tacs.tacsthree.connector.MarvelConnector;
import com.utn.tacs.tacsthree.connector.api.MarvelApiCharacter;
import com.utn.tacs.tacsthree.connector.api.MarvelImage;
import com.utn.tacs.tacsthree.models.MarvelCharacter;

@RunWith(MockitoJUnitRunner.class)
public class MarvelServiceTest {

	private MarvelService service = new MarvelService();

	@Mock
	private MarvelConnector connector;

	@Before
	public void before() {
		reset(connector);
		service.setConnector(connector);

		MarvelApiCharacter character = new MarvelApiCharacter();
		character.setName("Iron Man");
		character.setDescription("Description");
		character.setThumbnail(new MarvelImage("test", "jpg"));

		when(this.connector.getCharacter(eq(20L))).thenReturn(character);
		when(this.connector.getAllCharacters()).thenReturn(newArrayList(character));
	}

	@Test
	public void getCharacter() {
		MarvelCharacter character = service.getCharacter(20L);

		assertNotNull(character);
		assertEquals("Iron Man", character.getName());
		assertEquals("Description", character.getDescription());
		assertEquals("test/standard_amazing.jpg", character.getThumbnailUrl());
	}

	@Test
	public void getCharacters() {
		List<MarvelCharacter> characters = service.getAllCharacters();

		assertNotNull(characters);
		assertFalse(characters.isEmpty());
		assertEquals(1, characters.size());
	}
}