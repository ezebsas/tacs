package com.utn.tacs.tacsthree.api.v1.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import com.utn.tacs.tacsthree.exceptions.DuplicateTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;
import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.TacsModel;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.CharacterGroupDAO;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;

public class CharacterGroupsControllers {

	private CharacterGroupDAO repository;
	private MarvelCharacterDAO characterRepo;

	public CharacterGroupsControllers(CharacterGroupDAO _repository, MarvelCharacterDAO _characterRepo) {
		this.repository = _repository;
		this.characterRepo = _characterRepo;
	}

	public List<CharacterGroup> getAllGroups() {
		return repository.get();
	}

	public CharacterGroup getGroup(String _id) throws InexistentTacsModelException {
		return repository.get(new CharacterGroup(_id));
	}

	public CharacterGroup createGroup(CharacterGroup group) throws InvalidTacsModelException {
		group.valid();
		try {
			getGroup(group.getId());
		} catch (InexistentTacsModelException e) {
			repository.save(group);
			return group;
		}
		throw new DuplicateTacsModelException("user already exists");
	}

	public List<CharacterGroup> updateGroups(List<CharacterGroup> groupList) {
		groupList.forEach(u -> updateGroup(u));
		return groupList;
	}

	public CharacterGroup updateGroup(CharacterGroup group)
			throws InexistentTacsModelException, InvalidTacsModelException {
		group.valid();
		repository.get(group);
		repository.save(group);
		return group;

	}

	public void deleteAllGroups() {
		repository.delete();
	}

	public void deleteGroup(String _id) throws InexistentTacsModelException {
		repository.delete(new CharacterGroup(_id));
	}

	// Group Characters

	public List<MarvelCharacter> getCharacters(String _id) throws InexistentTacsModelException {
		return getGroup(_id).getCharacters();
	}

	public CharacterGroup addCharacter(String _id, TacsModel _character)
			throws InexistentTacsModelException, InvalidTacsModelException, DuplicateTacsModelException {
		CharacterGroup group = getGroup(_id);
		MarvelCharacter obtainedCharacter = null;
		try {
			obtainedCharacter = characterRepo.get(_character);
			try {
				group.getCharacter(_character);
				throw new DuplicateTacsModelException("character already in group");
			} catch (InexistentTacsModelException e) {
				group.addCharacters(obtainedCharacter);
				return updateGroup(group);
			}
		} catch (InexistentTacsModelException e) {
			throw new InvalidTacsModelException("character doesn't exist");
		}
	}

	public void removeCharacters(String _id) throws InexistentTacsModelException, InvalidTacsModelException {
		CharacterGroup group = getGroup(_id);
		group.removeAllCharacters();
		updateGroup(group);
	}

	public void removeCharacter(String _groupId, String _characterId)
			throws InexistentTacsModelException, InvalidTacsModelException {
		CharacterGroup group = getGroup(_groupId);
		TacsModel character = characterRepo.get(new MarvelCharacter(_characterId));
		group.removeCharacters(character);
		updateGroup(group);
	}
}
