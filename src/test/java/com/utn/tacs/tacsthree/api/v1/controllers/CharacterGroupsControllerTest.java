package com.utn.tacs.tacsthree.api.v1.controllers;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;
import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.CharacterGroupDAO;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;
import com.utn.tacs.tacsthree.persistence.UserDAO;
import com.utn.tacs.tacsthree.persistence.mocks.CharacterGroupTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.MarvelCharacterTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.UserTestRepository;

public class CharacterGroupsControllerTest {

	private CharacterGroupsController controller;
	private CharacterGroupDAO groupRepo = new CharacterGroupTestRepository();
	private MarvelCharacterDAO characRepo = new MarvelCharacterTestRepository();
	private UserDAO usersRepo = new UserTestRepository();

	@Before
	public void setUp() {
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

		((CharacterGroupTestRepository) groupRepo).groupList.clear();
		Integer index = 0;
		for (MarvelCharacter _charac : characRepo.get()) {
			CharacterGroup group = new CharacterGroup();
			group.setId((index++).toString() + "709b8799a96331925075510");
			group.setName("Group of " + _charac.getName());
			group.addCharacters(_charac);
			((CharacterGroupTestRepository) groupRepo).groupList.add(group);
		}
		controller = new CharacterGroupsController(groupRepo, characRepo);
	}

	@Test
	public void testGetAllGroups() {
		assertEquals(((CharacterGroupTestRepository) groupRepo).groupList.size(), controller.getAllGroups().size());
	}

	@Test
	public void getGroupCorrectly() {
		CharacterGroup _group = controller.getGroup("0709b8799a96331925075510");
		assertNotEquals(null, _group.getName());
		assertNotEquals(null, _group.getId());
	}

	@Test(expected = InexistentTacsModelException.class)
	public void getInexistentGroup() {
		controller.getGroup("0709b8799a96331925075511");
	}

	@Test
	public void createValidGroup() {
		Integer groups_amount = ((CharacterGroupTestRepository) groupRepo).groupList.size();
		CharacterGroup group = new CharacterGroup("0709b8799a96331925075511");
		group.setName("Tester");
		group.addCharacters(characRepo.get().get(0));
		controller.createGroup(group);
		assertEquals(groups_amount + 1, ((CharacterGroupTestRepository) groupRepo).groupList.size(), 0);
	}

	@Test(expected = InvalidTacsModelException.class)
	public void createInvalidGroupNullName() {
		controller.createGroup(new CharacterGroup("0709b8799a96331925075511"));
	}

	@Test(expected = InvalidTacsModelException.class)
	public void createInvalidGroupEmptyCharacters() {
		CharacterGroup group = new CharacterGroup("0709b8799a96331925075511");
		group.setName("Tester");
		controller.createGroup(group);
	}

	@Test
	public void updateValidGroup() {
		CharacterGroup group = controller.getGroup("0709b8799a96331925075510");
		group.setName("Group with different name");
		CharacterGroup updateResult = controller.updateGroup(group);
		assertEquals("Group with different name", updateResult.getName());
	}

	@Test(expected = InvalidTacsModelException.class)
	public void updateInvalidGroupNullName() {
		controller.updateGroup(new CharacterGroup("0709b8799a96331925075510"));
	}

	@Test(expected = InvalidTacsModelException.class)
	public void updateInvalidGroupEmptyCharacters() {
		CharacterGroup group = new CharacterGroup("0709b8799a96331925075510");
		group.setName("Tester");
		controller.createGroup(group);
	}

	@Test(expected = InexistentTacsModelException.class)
	public void updateInexistentGroup() {
		CharacterGroup group = new CharacterGroup("123ab8799a96331925075301");
		group.setName("Tester");
		group.addCharacters(characRepo.get().get(1));
		controller.updateGroup(group);
	}

	@Test
	public void deleteGroup() {
		controller.getGroup("0709b8799a96331925075510");
		controller.deleteGroup("0709b8799a96331925075510", Arrays.asList(usersRepo));
		try {
			controller.getGroup("5709b8799a96331925075301");
		} catch (InexistentTacsModelException e) {
			assertEquals("get failed", e.getMessage());
		}
	}

	@Test(expected = InexistentTacsModelException.class)
	public void deleteInexistentGroup() {
		controller.deleteGroup("123ab8799a96331925075301", Arrays.asList(usersRepo));
	}

	@Test
	public void getCharactersOfGroup() {
		assertEquals(controller.getGroup("0709b8799a96331925075510").getCharacters().size(),
				controller.getCharacters("0709b8799a96331925075510").size());
	}

	@Test(expected = InexistentTacsModelException.class)
	public void getCharacterOfInvalidGroup() {
		controller.getCharacters("123ab1111a96331925075301");
	}

	@Test
	public void addCharacterOfGroup() {
		controller.addCharacter("0709b8799a96331925075510", characRepo.get().get(1));
		assertEquals(2, controller.getGroup("0709b8799a96331925075510").getCharacters().size());
	}

	@Test(expected = InexistentTacsModelException.class)
	public void addCharacterToInexistentGroup() {
		controller.addCharacter("123ab8788a96331925075301", characRepo.get().get(1));
	}

	@Test(expected = InvalidTacsModelException.class)
	public void addInvalidCharacterToGroup() {
		controller.addCharacter("0709b8799a96331925075510", new MarvelCharacter());
	}

	@Test
	public void removeCharacterFromGroup() {
		controller.addCharacter("0709b8799a96331925075510", characRepo.get().get(1));
		assertEquals(2, controller.getGroup("0709b8799a96331925075510").getCharacters().size());
		controller.removeCharacter("0709b8799a96331925075510", characRepo.get().get(1).getIdMarvel());
		assertEquals(1, controller.getGroup("0709b8799a96331925075510").getCharacters().size());
	}

	@Test(expected = InexistentTacsModelException.class)
	public void removeInexistentCharacterOfGroup() {
		controller.removeCharacter("0709b8799a96331925075510", 123L);
	}

	@Test(expected = InexistentTacsModelException.class)
	public void removeInexistentCharacterOfUser() {
		controller.removeCharacter("5709b8799a96331925075301", characRepo.get().get(1).getIdMarvel());
	}

	@Test
	public void deleteGroupImpactsOwnerUser() throws Exception {
		User randomUser = usersRepo.get().get(0);
		CharacterGroup _tempGroup = controller.getGroup("0709b8799a96331925075510");
		randomUser.addGroup(_tempGroup);
		usersRepo.save(randomUser);
		controller.deleteGroup("0709b8799a96331925075510", Arrays.asList(usersRepo));
		try {
			usersRepo.get(randomUser).getGroup(_tempGroup);
			fail("Group is still attached to user.");
		} catch (InexistentTacsModelException e) {
		}
	}
}
