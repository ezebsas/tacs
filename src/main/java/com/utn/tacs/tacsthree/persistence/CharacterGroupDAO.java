package com.utn.tacs.tacsthree.persistence;

import java.util.List;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.CharacterGroup;

public interface CharacterGroupDAO {

	List<CharacterGroup> get();

	CharacterGroup get(CharacterGroup group) throws InexistentTacsModelException;

	void save(CharacterGroup group);

	void delete(CharacterGroup group) throws InexistentTacsModelException;

	void delete();

}
