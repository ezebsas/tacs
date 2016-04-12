package com.utn.tacs.tacsthree.persistence.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.persistence.CharacterGroupDAO;

public class CharacterGroupTestRepository implements CharacterGroupDAO {

	public static CharacterGroupTestRepository instance = new CharacterGroupTestRepository();

	public static CharacterGroupTestRepository getInstance() {
		return instance;
	}

	private List<CharacterGroup> groupList = new ArrayList<CharacterGroup>();

	public void restart() {
		groupList.clear();
	}

	private CharacterGroupTestRepository() {
		restart();
	}

	@Override
	public List<CharacterGroup> get() {
		return groupList;
	}

	@Override
	public CharacterGroup get(CharacterGroup group) throws InexistentTacsModelException {
		try {
			return groupList.stream().filter(o -> o.getId().equals(group.getId())).findFirst().get();
		} catch (NoSuchElementException e) {
			throw new InexistentTacsModelException("get failed");
		}
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
			CharacterGroup _group = groupList.stream().filter(o -> o.getId().equals(group.getId())).findFirst().get();
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
