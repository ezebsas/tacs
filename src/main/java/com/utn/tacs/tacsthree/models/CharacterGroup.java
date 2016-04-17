package com.utn.tacs.tacsthree.models;

import java.util.ArrayList;
import java.util.List;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
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

	public MarvelCharacter getCharacter(TacsModel _character) throws InexistentTacsModelException {
		for (MarvelCharacter character : getCharacters())
			if (character.sameModels(_character))
				return character;
		throw new InexistentTacsModelException("character is not in group");
	}

	public Boolean removeCharacters(TacsModel _charact) {
		return this.characters.remove(_charact);
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

}
