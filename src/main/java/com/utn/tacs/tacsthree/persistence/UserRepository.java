package com.utn.tacs.tacsthree.persistence;

import org.mongodb.morphia.Datastore;

import com.utn.tacs.tacsthree.models.User;

public class UserRepository {

	private Datastore datastore;

	public UserRepository(Datastore _datastore) {
		this.datastore = _datastore;
	}

	public void save(User user) {
		datastore.save(user);
	}

	public User get(User user) {
		return datastore.get(user);
	}
}
