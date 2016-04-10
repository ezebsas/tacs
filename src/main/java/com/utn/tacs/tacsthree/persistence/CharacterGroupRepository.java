package com.utn.tacs.tacsthree.persistence;

import org.mongodb.morphia.Datastore;

import com.utn.tacs.tacsthree.models.CharacterGroup;

public class CharacterGroupRepository {

	private Datastore datastore;

	public CharacterGroupRepository(Datastore _datastore) {
		this.datastore = _datastore;
	}

	public void save(CharacterGroup group) {
		datastore.save(group);
	}

	public CharacterGroup get(CharacterGroup group) {
		return datastore.get(group);
	}
}
