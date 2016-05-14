package com.utn.tacs.tacsthree.persistence.mocks;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.persistence.CharacterGroupDAO;

public class CharacterGroupTestRepository implements CharacterGroupDAO {

	public List<CharacterGroup> groupList = new ArrayList<CharacterGroup>();

	@Inject
	public CharacterGroupTestRepository() {
		restart();
	}

	public void restart() {
		groupList.clear();
		Integer index = 0;
		for (MarvelCharacter _charac : new MarvelCharacterTestRepository().get()) {
			CharacterGroup group = new CharacterGroup();
			group.setId((index++).toString() + "709b8799a96331925075510");
			group.setName("Group of " + _charac.getName());
			group.addCharacters(_charac);
			groupList.add(group);
		}
	}

	@Override
	public List<CharacterGroup> get() {
		return groupList;
	}

	@Override
	public CharacterGroup get(CharacterGroup group) throws InexistentTacsModelException {
		for (CharacterGroup _group : groupList)
			if (_group.sameModels(group))
				return _group;
		throw new InexistentTacsModelException("get failed");
	}

	@Override
	public void save(CharacterGroup group) {
		try {
			CharacterGroup _group = get(group);
			groupList.remove(_group);
		} catch (InexistentTacsModelException e) {
			// Ok! So it doesn't exist, let's create it!
		}
		groupList.add(group);
	}

	@Override
	public void delete(CharacterGroup group) throws InexistentTacsModelException {
		try {
			CharacterGroup _group = get(group);
			groupList.remove(_group);
		} catch (InexistentTacsModelException e) {
			throw new InexistentTacsModelException("delete failed");
		}
	}

	@Override
	public void delete() {
		groupList.clear();
	}

}
