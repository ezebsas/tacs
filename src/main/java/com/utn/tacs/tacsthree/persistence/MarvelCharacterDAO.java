package com.utn.tacs.tacsthree.persistence;

import java.util.List;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.TacsModel;

public interface MarvelCharacterDAO {

	List<MarvelCharacter> get();

	MarvelCharacter get(TacsModel _character) throws InexistentTacsModelException;
}
