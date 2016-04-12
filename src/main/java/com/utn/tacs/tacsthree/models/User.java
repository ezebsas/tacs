package com.utn.tacs.tacsthree.models;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

import org.mongodb.morphia.annotations.Entity;

@Entity
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

	public MarvelCharacter getFavorite(MarvelCharacter _charact) throws NoSuchElementException {
		return getFavorites().stream().filter(u -> u.getId().equals(_charact.getId())).findFirst().get();
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