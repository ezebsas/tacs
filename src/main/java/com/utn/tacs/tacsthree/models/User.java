package com.utn.tacs.tacsthree.models;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

@Entity
public class User extends TacsModel {

	private String name = null;
	private String password = null;
	private List<MarvelCharacter> characters = new ArrayList<MarvelCharacter>();
	private List<CharacterGroup> groups = new ArrayList<CharacterGroup>();
	private Integer groupsHistoric = 0;

	public User() {
	}

	public User(String _id) {
		try {
			setId(_id);
		} catch (IllegalArgumentException e) {
			// ID will be null
		}
	}

	public User(String _id, String _name) {
		setId(_id);
		setName(_name);
	}

	public User(String _id, String _name, String _password) {
		setId(_id);
		setName(_name);
		setPassword(_password);
	}

	public String getName() {
		return name;
	}

	public void setName(String _name) {
		this.name = _name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String _password) {
		this.password = _password;
	}

	public List<MarvelCharacter> getCharacters() {
		return characters;
	}

	public void addCharacter(MarvelCharacter _charact) {
		this.characters.add(_charact);
	}

	public void removeCharacter(MarvelCharacter _charact) throws InexistentTacsModelException {
		MarvelCharacter chosen = null;
		for (MarvelCharacter character : getCharacters())
			if (character.sameModels(_charact))
				chosen = character;
		if (chosen == null)
			throw new InexistentTacsModelException("Character isn't favorite of " + getName());
		this.characters.remove(chosen);
	}

	public void removeCharacters() {
		this.characters.clear();
	}

	public List<CharacterGroup> getGroups() {
		return groups;
	}

	public CharacterGroup getGroup(CharacterGroup _group) throws InexistentTacsModelException {
		for (CharacterGroup group : getGroups()) {
			if (group.getId().equals(_group.getId()))
				return group;
		}
		throw new InexistentTacsModelException("group was not created by user: " + getName());
	}

	public void addGroup(CharacterGroup _group) {
		this.groups.add(_group);
	}

	public void removeGroup(CharacterGroup _group) {
		CharacterGroup chosen = null;
		for (CharacterGroup group : getGroups()) {
			if (group.sameModels(_group))
				chosen = group;
		}
		if (chosen == null)
			throw new InexistentTacsModelException("Group wasn't created by " + getName());
		this.groups.remove(chosen);
	}

	public void removeGroup() {
		this.groups.clear();
	}

	public MarvelCharacter getCharacter(MarvelCharacter _charact) throws InexistentTacsModelException {
		for (MarvelCharacter character : getCharacters()) {
			if (character.getIdMarvel().equals(_charact.getIdMarvel()))
				return character;
		}
		throw new InexistentTacsModelException("character is not favorite of user: " + getName());
	}

	public Integer getGroupsHistoric() {
		return groupsHistoric;
	}

	public void setGroupsHistoric(Integer groupsHistoric) {
		this.groupsHistoric = groupsHistoric;
	}

	@Override
	public void valid() {
		if (this.getName() == null)
			throw new InvalidTacsModelException("invalid name");
		if (this.getCharacters() == null)
			throw new InvalidTacsModelException("invalid favorite list");
		if (this.getGroups() == null)
			throw new InvalidTacsModelException("invalid group list");
	}
}