package com.utn.tacs.tacsthree.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

public class User extends TacsModel {

	private String name = null;
	private List<MarvelCharacter> favoriteCharacters = new ArrayList<MarvelCharacter>();

	public User() {
	}

	public User(String _id) {
		setId(_id);
	}

	public User(String _id, String _name) {
		setId(_id);
		setName(_name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MarvelCharacter> getFavorites() {
		return favoriteCharacters;
	}

	public void addFavorite(MarvelCharacter _charact) {
		this.favoriteCharacters.add(_charact);
	}

	public Boolean removeFavorite(MarvelCharacter _charact) {
		return this.favoriteCharacters.remove(_charact);
	}

	public void removeFavorites() {
		this.favoriteCharacters.clear();
	}

	public MarvelCharacter getFavorite(Integer id)
			throws NoSuchElementException, JsonGenerationException, JsonMappingException, IOException {
		return favoriteCharacters.stream().filter(u -> u.getId().equals(id.toString())).findFirst().get();
	}

	@Override
	public void valid() {
		if (this.getId() == null)
			throw new InvalidTacsModelException("invalid id");
		if (this.getName() == null)
			throw new InvalidTacsModelException("invalid name");
		if (this.getFavorites() == null)
			throw new InvalidTacsModelException("invalid favorites");
	}
}