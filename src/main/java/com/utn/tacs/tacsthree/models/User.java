package com.utn.tacs.tacsthree.models;

import java.util.ArrayList;
import java.util.List;

public class User extends TacsModel {

	private String name = null;
	private List<MarvelCharacter> favoriteCharacters = new ArrayList<MarvelCharacter>();

	public User() {
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
	
	public User actualizarCon(User usuario){
		this.setName(usuario.getName());
		return this;
	}
	
	@Override
	public Boolean isValid() {
		if (this.getId() == null)
			return false;
		if (this.getName() == null)
			return false;
		if (this.getFavorites() == null)
			return false;
		return true;
	}
}