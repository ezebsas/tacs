package com.utn.tacs.tacsthree.models;

import java.util.ArrayList;
import java.util.List;
import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;
import org.mongodb.morphia.annotations.Entity;

@Entity
public class CharacterGroup extends TacsModel {

	private String name = null;
	private List<MarvelCharacter> characters = new ArrayList<MarvelCharacter>();

	public CharacterGroup() {
	}

	public CharacterGroup(String _id) {
		setId(_id);
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

	public Boolean removeCharacters(TacsModel _charact) {
		return this.characters.remove(_charact);
	}

	public void removeAllCharacters() {
		this.characters.clear();
	}

	@Override
	public void valid() {
		if (this.getId() == null)
			throw new InvalidTacsModelException("invalid id");
		if (this.getName() == null)
			throw new InvalidTacsModelException("invalid name");
		if (this.getCharacters().isEmpty())
			throw new InvalidTacsModelException("invalid characters");
	}

	public TacsModel getCharacter(TacsModel _character) {
		return getCharacters().stream().filter(u -> u.getId().equals(_character.getId())).findFirst().get();
	}

}
