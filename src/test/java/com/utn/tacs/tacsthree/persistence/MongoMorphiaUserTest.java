package com.utn.tacs.tacsthree.persistence;

import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.utn.tacs.tacsthree.models.User;

public class MongoMorphiaUserTest {
	
	TacsDB repository;
	
	@Before
	public void setUp() throws Exception {
		MongoClient client = new MongoClient();
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.utn.tacs.tacsthree.models");
		Datastore datastore = morphia.createDatastore(client, "tacs_test");
		repository = new TacsMongoDB(datastore);
	}

	@Test
	public void validUserIsPersisted() {
		User testSubject = new User("1", "Test Name");
		repository.save(testSubject);
	}

}
