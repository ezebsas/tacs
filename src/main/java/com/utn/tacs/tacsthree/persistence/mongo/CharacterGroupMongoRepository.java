package com.utn.tacs.tacsthree.persistence.mongo;

import org.mongodb.morphia.Datastore;

import com.utn.tacs.tacsthree.models.CharacterGroup;

public class CharacterGroupMongoRepository {

	private Datastore datastore;

	public CharacterGroupMongoRepository(Datastore _datastore) {
		this.datastore = _datastore;
	}

	public void save(CharacterGroup group) {
		datastore.save(group);
	}

	public CharacterGroup get(CharacterGroup group) {
		return datastore.get(group);
	}
}
