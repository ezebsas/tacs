package com.utn.tacs.tacsthree.models;

import java.util.ArrayList;
import java.util.List;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class User extends TacsModel {

	private String name = null;
	private List<MarvelCharacter> characters = new ArrayList<MarvelCharacter>();

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

	public List<MarvelCharacter> getCharacters() {
		return characters;
	}

	public void addCharacter(MarvelCharacter _charact) {
		this.characters.add(_charact);
	}

	public Boolean removeCharacter(TacsModel _charact) {
		return this.characters.remove(_charact);
	}

	public void removeCharacters() {
		this.characters.clear();
	}

	public TacsModel getCharacter(TacsModel _charact) throws InexistentTacsModelException {
		for (TacsModel character : getCharacters()) {
			if (character.getId().equals(_charact.getId()))
				return character;
		}
		throw new InexistentTacsModelException("character is not favorite of user: " + getName());
	}

	@Override
	public void valid() {
		if (this.getId() == null)
			throw new InvalidTacsModelException("invalid id");
		if (this.getName() == null)
			throw new InvalidTacsModelException("invalid name");
		if (this.getCharacters() == null)
			throw new InvalidTacsModelException("invalid favorites");
	}
}