package com.utn.tacs.tacsthree.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

public class CharacterGroup extends TacsModel {

	private String name = null;
	private List<MarvelCharacter> characters = new ArrayList<MarvelCharacter>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MarvelCharacter> getCharacters() {
		return characters;
	}

	public void addCharacters(MarvelCharacter _charact) {
		this.characters.add(_charact);
	}

	public Boolean removeCharacters(MarvelCharacter _charact) {
		return this.characters.remove(_charact);
	}
	
	public void removeAllCharacters() {
		this.characters.clear();
	}

	public Boolean removeFavoriteById(String _id) throws JsonGenerationException, JsonMappingException, NumberFormatException, NoSuchElementException, IOException{
		return this.removeCharacters(this.getFavorite(Integer.valueOf(_id)));
	}
	
	public MarvelCharacter getFavorite(Integer id)
			throws NoSuchElementException, JsonGenerationException, JsonMappingException, IOException {
		return characters.stream().filter(u -> u.getId().equals(id.toString())).findFirst().get();
	}
	
	@Override
	public Boolean isValid() {
		if (this.getId() == null)
			return false;
		if (this.getName() == null)
			return false;
		if (this.getCharacters().isEmpty())
			return false;
		return true;
	}

}
