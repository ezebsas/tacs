package com.utn.tacs.tacsthree.persistence.mongo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.mongo.UserMongoRepository;

public class UserMongoMorphiaTest {

	MongoClient client = new MongoClient();
	Datastore datastore;
	UserMongoRepository repository;

	@Before
	public void setUp() {
		dropTestDB();
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.utn.tacs.tacsthree.models");
		datastore = morphia.createDatastore(client, "tacs_test");
		repository = new UserMongoRepository(datastore);
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
		assertEquals("Favourites size isn't same", testSubject.getCharacters().size(),
				persistedUser.getCharacters().size());
	}

	@Test
	public void getNonPersistedUser() {
		User user = new User("5709b8799a96331925075306", "Test Subject");
		try {
			repository.get(user);
			fail("Inexistent Exception not throwed");
		} catch (InexistentTacsModelException e) {
			assertEquals("get failed", e.getMessage());
		}
	}

	@Test
	public void deletePersistedUser() {
		User testSubject = new User();
		testSubject.setName("Test Subject");
		repository.save(testSubject);
		assertNotEquals("Id is null", null, testSubject.getId());
		repository.delete(testSubject);
		try {
			repository.get(testSubject);
			fail("user still exists");
		} catch (InexistentTacsModelException e) {
			assertEquals("get failed", e.getMessage());
		}
	}

	@Test
	public void deleteNonPersistedUser() {
		User testSubject = new User("5709b8799a96331925075306", "Test Subject");
		try {
			repository.delete(testSubject);
			fail("user was deleted");
		} catch (InexistentTacsModelException e) {
			assertEquals("delete failed", e.getMessage());
		}
	}

	@Test
	public void deleteAllUsers() {
		User testSubject1 = new User();
		testSubject1.setName("Test Subject 1");
		User testSubject2 = new User();
		testSubject2.setName("Test Subject 2");
		assertEquals("Id is null", null, testSubject1.getId());
		assertEquals("Id is null", null, testSubject2.getId());
		repository.save(testSubject1);
		repository.save(testSubject2);
		repository.delete();
		try {
			repository.get(testSubject1);
			fail("users still exists");
		} catch (InexistentTacsModelException e1) {
			try {
				repository.get(testSubject2);
				fail("users still exists");
			} catch (InexistentTacsModelException e2) {
				assertEquals("get failed", e1.getMessage());
				assertEquals("get failed", e2.getMessage());
			}
		}
	}
}
