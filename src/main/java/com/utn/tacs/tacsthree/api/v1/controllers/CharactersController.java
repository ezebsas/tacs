package com.utn.tacs.tacsthree.api.v1.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.MarvelCharacter;

public class CharactersController extends CommonController{
	private List<MarvelCharacter> personajes = new ArrayList<MarvelCharacter>();

	public CharactersController() {
		MarvelCharacter spiderMan = new MarvelCharacter("1", "Peter Benjamin Parker"
				,"Peter can cling to most surfaces, has superhuman strength...");
		MarvelCharacter hulk = new MarvelCharacter("2", "Robert Bruce Banner"
				,"Dr. Bruce Banner is a genius in nuclear physics, possessing a ...");
				
		personajes.add(spiderMan);
		personajes.add(hulk);
		
	}

	public List<MarvelCharacter> getAllCharacters() throws JsonGenerationException, JsonMappingException, IOException {
		return personajes;
	}

	public MarvelCharacter getCharacter(Integer id)
			throws NoSuchElementException, JsonGenerationException, JsonMappingException, IOException {
		return personajes.stream().filter(c -> c.getId().equals(id.toString())).findFirst().get();
	}
	
}
