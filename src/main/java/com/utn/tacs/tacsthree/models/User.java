package com.utn.tacs.tacsthree.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class User extends TacsModel {

	private String name = null;
	private List<MarvelCharacter> favoriteCharacters = new ArrayList<MarvelCharacter>();
        private String encryptedPassword= null;
        private Date current_sign_in_at; 
                
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
        
        public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
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