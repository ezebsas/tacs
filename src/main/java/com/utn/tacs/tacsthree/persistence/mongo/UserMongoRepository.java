package com.utn.tacs.tacsthree.persistence.mongo;

import org.mongodb.morphia.Datastore;

import com.utn.tacs.tacsthree.models.User;

public class UserMongoRepository {

	private Datastore datastore;

	public UserMongoRepository(Datastore _datastore) {
		this.datastore = _datastore;
	}

	public void save(User user) {
		datastore.save(user);
	}

	public User get(User user) {
		return datastore.get(user);
	}
}
