package com.utn.tacs.tacsthree.persistence.mongo;

import java.util.List;

import org.mongodb.morphia.Datastore;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.persistence.CharacterGroupDAO;

public class CharacterGroupMongoRepository implements CharacterGroupDAO {
	private Datastore datastore;

	public CharacterGroupMongoRepository(Datastore _datastore) {
		this.datastore = _datastore;
	}

	public void save(CharacterGroup group) {
		datastore.save(group);
	}

	public CharacterGroup get(CharacterGroup group) throws InexistentTacsModelException {
		return datastore.get(group);
	}

	@Override
	public List<CharacterGroup> get() {
		return datastore.find(CharacterGroup.class).asList();
	}

	@Override
	public void delete(CharacterGroup group) throws InexistentTacsModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}
}
