package com.utn.tacs.tacsthree.persistence.mongo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.persistence.mongo.CharacterGroupMongoRepository;

public class CharacterGroupMongoMorphiaTest {

	MongoClient client = new MongoClient();
	Datastore datastore;
	CharacterGroupMongoRepository repository;

	@Before
	public void setUp() {
		dropTestDB();
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.utn.tacs.tacsthree.models");
		datastore = morphia.createDatastore(client, "tacs_test");
		repository = new CharacterGroupMongoRepository(datastore);
	}

	@After
	public void dropTestDB() {
		client.dropDatabase("tacs_test");
	}

	@Test
	public void groupIsPersisted() {
		CharacterGroup testGroup = new CharacterGroup();
		testGroup.setName("Test Group");
		assertEquals("Id isn't null", null, testGroup.getId());
		repository.save(testGroup);
		assertNotEquals("Id isn't null", null, testGroup.getId());
	}

	@Test
	public void getPersistedGroup() {
		CharacterGroup testGroup = new CharacterGroup();
		testGroup.setName("Test Group");
		assertEquals("Id isn't null", null, testGroup.getId());
		repository.save(testGroup);
		CharacterGroup persistedUser = datastore.get(testGroup);
		assertEquals("Id isn't same", testGroup.getId(), persistedUser.getId());
		assertEquals("Name isn't same", testGroup.getName(), persistedUser.getName());
	}
}
