package com.utn.tacs.tacsthree.api.v1.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.utn.tacs.tacsthree.models.BuilderCharacter;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.MarvelCharacter;

public class CharactersController extends CommonController{
	private List<MarvelCharacter> personajes = new ArrayList<MarvelCharacter>();

	public CharactersController() {
		MarvelCharacter spiderMan = new BuilderCharacter()
			.definirId("1")
			.definirNombre("Peter Benjamin Parker")
			.definirDescripcion("Peter can cling to most surfaces, has superhuman strength...")
			.buildConValidacion();
		MarvelCharacter hulk = new BuilderCharacter()
			.definirId("2")
			.definirNombre("Robert Bruce Banner")
			.definirDescripcion("Dr. Bruce Banner is a genius in nuclear physics, possessing a ...")
			.buildConValidacion();
		
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
