package com.utn.tacs.tacsthree.persistence;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.utn.tacs.tacsthree.models.User;

public class UserMongoMorphiaTest {

	MongoClient client = new MongoClient();
	Datastore datastore;
	UserRepository repository;

	@Before
	public void setUp() {
		dropTestDB();
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.utn.tacs.tacsthree.models");
		datastore = morphia.createDatastore(client, "tacs_test");
		repository = new UserRepository(datastore);
	}

	@After
	public void dropTestDB() {
		client.dropDatabase("tacs_test");
	}

	@Test
	public void userIsPersisted() {
		User testSubject = new User();
		testSubject.setName("Test Subject");
		assertEquals("Id isn't null", null, testSubject.getId());
		repository.save(testSubject);
		assertNotEquals("Id isn't null", null, testSubject.getId());
	}

	@Test
	public void getPersistedUser() {
		User testSubject = new User();
		testSubject.setName("Test Subject");
		assertEquals("Id isn't null", null, testSubject.getId());
		repository.save(testSubject);
		User persistedUser = datastore.get(testSubject);
		assertEquals("Id isn't same", testSubject.getId(), persistedUser.getId());
		assertEquals("Name isn't same", testSubject.getName(), persistedUser.getName());
		assertEquals("Favourites size isn't same", testSubject.getFavorites().size(),
				persistedUser.getFavorites().size());
	}
}
