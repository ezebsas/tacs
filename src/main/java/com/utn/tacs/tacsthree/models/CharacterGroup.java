package com.utn.tacs.tacsthree.models;

import java.util.ArrayList;
import java.util.List;

public class CharacterGroup implements TacsModel {

	private String id = null;
	private String name = null;
	private List<MarvelCharacter> characters = new ArrayList<MarvelCharacter>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
