package com.utn.tacs.tacsthree.persistence;

import java.util.List;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;

public interface MarvelCharacterDAO {

	List<MarvelCharacter> get();

	MarvelCharacter get(MarvelCharacter _character) throws InexistentTacsModelException;

}