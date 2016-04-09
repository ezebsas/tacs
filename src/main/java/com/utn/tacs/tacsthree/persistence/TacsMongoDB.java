package com.utn.tacs.tacsthree.persistence;

import org.mongodb.morphia.Datastore;

public class TacsMongoDB implements TacsDB {

	private Datastore datastore;

	public TacsMongoDB(Datastore _datastore) {
		this.datastore = _datastore;
	}

	@Override
	public void save(Object object) {
		datastore.save(object);
	}
}
