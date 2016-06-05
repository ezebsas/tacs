package com.utn.tacs.tacsthree.api.v1.controllers;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;
import com.utn.tacs.tacsthree.persistence.UserDAO;
import com.utn.tacs.tacsthree.persistence.mocks.MarvelCharacterTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.UserTestRepository;

public class UsersControllerTest {

	private UsersController controller;
	private UserDAO userRepo = new UserTestRepository();
	private MarvelCharacterDAO characRepo = new MarvelCharacterTestRepository();

	@Before
	public void setUp() {
		((UserTestRepository) userRepo).delete();
		((UserTestRepository) userRepo).userList.add(new User("5709b8799a96331925075301", "Tom"));
		((UserTestRepository) userRepo).userList.add(new User("5709b8799a96331925075302", "Seba"));
		((UserTestRepository) userRepo).userList.add(new User("5709b8799a96331925075303", "Fabi"));
		((UserTestRepository) userRepo).userList.add(new User("5709b8799a96331925075304", "Eze"));
		((UserTestRepository) userRepo).userList.add(new User("5709b8799a96331925075305", "Ramiro"));
		((UserTestRepository) userRepo).userList.add(new User("5709b8799a96331925075306", "Facu"));

		((MarvelCharacterTestRepository) characRepo).characters.clear();
		MarvelCharacter peterCharacter = new MarvelCharacter("1309b8799a96331925075301", 1009491L, "Peter Parker", "");
		peterCharacter.setModified(new Date(1315515501000L));
		peterCharacter.setResourceURI("http://gateway.marvel.com/v1/public/characters/1009491");
		peterCharacter.setThumbnailUrl(
				"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/standard_amazing.jpg");
		((MarvelCharacterTestRepository) characRepo).characters.add(peterCharacter);
		MarvelCharacter bruceCharacter = new MarvelCharacter("1309b8799a96331925075302", 1009167L, "Bruce Banner", "");
		bruceCharacter.setModified(new Date(1326594561000L));
		bruceCharacter.setThumbnailUrl(
				"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/standard_amazing.jpg");
		bruceCharacter.setResourceURI("http://gateway.marvel.com/v1/public/characters/1009167");
		((MarvelCharacterTestRepository) characRepo).characters.add(bruceCharacter);
		controller = new UsersController(userRepo, characRepo);
	}

	@Test
	public void testGetAllUsers() {
		assertEquals(((UserTestRepository) userRepo).userList.size(), controller.getAllUsers().size());
	}

	@Test
	public void getUserCorrectly() {
		User _user = controller.getUser("5709b8799a96331925075301");
		assertEquals("Tom", _user.getName());
		assertEquals("5709b8799a96331925075301", _user.getId());
	}

	@Test(expected = InexistentTacsModelException.class)
	public void getInexistentUser() {
		controller.getUser("5709b8799a96331925075507");
	}

	@Test
	public void createValidUser() {
		Integer users_amount = ((UserTestRepository) userRepo).userList.size();
		controller.createUser(new User("5709b8799a96331925075507", "Tester"));
		assertEquals(users_amount + 1, ((UserTestRepository) userRepo).userList.size(), 0);
	}

	@Test(expected = InvalidTacsModelException.class)
	public void createInvalidUser() {
		controller.createUser(new User("5709b8799a96331925075507"));
	}

	@Test
	public void updateValidUser() {
		User updateResult = controller.updateUser(new User("5709b8799a96331925075301", "Tom2"));
		assertEquals("Tom2", updateResult.getName());
		assertEquals("Tom2", controller.getUser(updateResult.getId()).getName());
	}

	@Test(expected = InvalidTacsModelException.class)
	public void updateInvalidUser() {
		controller.updateUser(new User("5709b8799a96331925075301"));

	}

	@Test(expected = InexistentTacsModelException.class)
	public void updateInexistentUser() {
		controller.updateUser(new User("123ab8799a96331925075301", "Tom2"));
	}

	@Test
	public void deleteUser() {
		controller.getUser("5709b8799a96331925075301");
		controller.deleteUser("5709b8799a96331925075301");
		try {
			controller.getUser("5709b8799a96331925075301");
		} catch (InexistentTacsModelException e) {
			assertEquals("get failed", e.getMessage());
		}
	}

	@Test(expected = InexistentTacsModelException.class)
	public void deleteInexistentUser() {
		controller.deleteUser("123ab8799a96331925075301");
	}

	@Test
	public void getCharacterOfUser() {
		User _user = new User("5709b8799a96331925075301", "Tom2");
		_user.addCharacter(new MarvelCharacter("5709b8799a96331925075301", 200L, "Test", "Hey Baby!"));
		controller.updateUser(_user);
		assertEquals(1, controller.getCharactersOf("5709b8799a96331925075301").size());
	}

	@Test(expected = InexistentTacsModelException.class)
	public void getCharacterOfInvalidUser() {
		controller.getCharactersOf("123ab1111a96331925075301");
	}

	@Test
	public void addCharacterOfUser() {
		controller.addCharacter("5709b8799a96331925075301",
				new MarvelCharacter("1309b8799a96331925075301", 1009491L, "Peter Parker", ""));
		assertEquals(1, controller.getUser("5709b8799a96331925075301").getCharacters().size());
	}

	@Test(expected = InexistentTacsModelException.class)
	public void addCharacterOfInexistentUser() {
		controller.addCharacter("123ab8788a96331925075301",
				new MarvelCharacter("123ab1111a96331925075301", 200L, "Test", "Hey Baby!"));
	}

	@Test(expected = InvalidTacsModelException.class)
	public void addCharacterOfInvalidUser() {
		controller.addCharacter("5709b8799a96331925075301", new MarvelCharacter());
	}

	@Test
	public void removeCharactersOfUser() {
		controller.addCharacter("5709b8799a96331925075301",
				new MarvelCharacter("1309b8799a96331925075301", 1009491L, "Peter Parker", ""));
		controller.addCharacter("5709b8799a96331925075301",
				new MarvelCharacter("1309b8799a96331925075302", 1009167L, "Bruce Banner", ""));
		assertEquals(2, controller.getUser("5709b8799a96331925075301").getCharacters().size());
		controller.removeCharactersOf("5709b8799a96331925075301");
		assertEquals(0, controller.getUser("5709b8799a96331925075301").getCharacters().size());
	}

	@Test(expected = InexistentTacsModelException.class)
	public void removeFavoritesOfInexistentUser() {
		controller.removeCharactersOf("123ab8788a96331925075301");
	}

	@Test
	public void removeCharacterOfUser() {
		controller.addCharacter("5709b8799a96331925075301", characRepo.get().get(0));
		controller.addCharacter("5709b8799a96331925075301", characRepo.get().get(1));
		assertEquals(2, controller.getUser("5709b8799a96331925075301").getCharacters().size());
		controller.removeCharacter("5709b8799a96331925075301", characRepo.get().get(0).getIdMarvel());
		assertEquals(1, controller.getUser("5709b8799a96331925075301").getCharacters().size());
	}

	@Test(expected = InexistentTacsModelException.class)
	public void removeCharacterOfInexistentUser() {
		controller.removeCharacter("123ab1111a96331925075302", 1234L);
	}

	@Test(expected = InexistentTacsModelException.class)
	public void removeInexistentCharacterOfUser() {
		controller.removeCharacter("5709b8799a96331925075301", 12311L);
	}
}
