package com.utn.tacs.tacsthree.api.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import com.utn.tacs.tacsthree.api.v1.controllers.CharacterGroupsController;
import com.utn.tacs.tacsthree.api.v1.controllers.MarvelCharactersController;
import com.utn.tacs.tacsthree.api.v1.controllers.ReportsController;
import com.utn.tacs.tacsthree.api.v1.controllers.UsersController;
import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.mocks.CharacterGroupTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.MarvelCharacterTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.UserTestRepository;

public class RouteProviderTest {

	private MarvelCharacterTestRepository characterRepository = new MarvelCharacterTestRepository();
	private UserTestRepository userRepository = new UserTestRepository();
	private CharacterGroupTestRepository groupRepository = new CharacterGroupTestRepository();

	private RouteProvider route;

	@Before
	public void setUp() {
		characterRepository.restart();
		userRepository.restart();
		groupRepository.restart();

		MarvelCharactersController characterController = new MarvelCharactersController(characterRepository);
		UsersController userController = new UsersController(userRepository, characterRepository);
		CharacterGroupsController groupsController = new CharacterGroupsController(groupRepository,
				characterRepository);
		ReportsController reportController = new ReportsController(userRepository, characterRepository);

		route = new RouteProvider(characterController, userController, groupsController, reportController);
	}

	@Test
	public void getUsersTest() {
		Response response = route.getUsers();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertNotEquals(null, response.getEntity());
	}

	@Test
	public void addUserTest() {
		Response response = route.addUser(new User("5709b8799a96331925075300", "Test Subject"));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void addUserTestCatchesInvalidUser() {
		Response response = route.addUser(new User("5709b8799a96331925075300"));
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void addUserTestCatchesDuplicatedUser() {
		Response response = route.addUser(new User("5709b8799a96331925075301", "Test Subject"));
		assertEquals(Status.CONFLICT.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateUsers() {
		List<User> list = new ArrayList<User>();
		list.add(new User("5709b8799a96331925075301", "Test Subject 1"));
		list.add(new User("5709b8799a96331925075302", "Test Subject 2"));
		Response response = route.updateUsers(list);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateUsersCatchesInvalidUser() {
		List<User> list = new ArrayList<User>();
		list.add(new User("5709b8799a96331925075301"));
		list.add(new User("5709b8799a96331925075302", "Test Subject 2"));
		Response response = route.updateUsers(list);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateUsersCatchesInexistentUser() {
		List<User> list = new ArrayList<User>();
		list.add(new User("5709b8799a96331925075300", "Test Subject 1"));
		list.add(new User("5709b8799a96331925075302", "Test Subject 2"));
		Response response = route.updateUsers(list);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteUsers() {
		Response response = route.deleteUsers();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertTrue(userRepository.get().isEmpty());
	}

	@Test
	public void getUser() {
		Response response = route.getUser("5709b8799a96331925075301");
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void getUserCatchesInexistentUser() {
		Response response = route.getUser("5709b8799a96331925075300");
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateUser() {
		User user = new User("5709b8799a96331925075301", "Test Subject 1");
		Response response = route.updateUser(user.getId(), user);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateUserCatchesInvalidPath() {
		User user = new User("5709b8799a96331925075301", "Test Subject 1");
		Response response = route.updateUser("5709b8799a96331925075302", user);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateUserCatchesInvalidUser() {
		User user = new User("5709b8799a96331925075301");
		Response response = route.updateUser(user.getId(), user);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateUserCatchesInexistentUser() {
		User user = new User("aaa9b8799a96331925075301", "Test Subject 1");
		Response response = route.updateUser(user.getId(), user);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateUserCatchesInexistentPath() {
		User user = new User("5709b8799a96331925075301", "Test Subject 1");
		Response response = route.updateUser("aaa9b8799a96331925075301", user);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteUser() {
		Response response = route.deleteUser("5709b8799a96331925075301");
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteUserCatchesInexistentUser() {
		Response response = route.deleteUser("5709b8799a96331925075300");
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void getUserCharacters() {
		Response response = route.getUserCharacters("5709b8799a96331925075301");
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void getUserCharactersCatchesInexistentUser() {
		Response response = route.getUserCharacters("5709b8799a96331925075300");
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void addUserCharacter() {
		Response response = route.addUserCharacter("5709b8799a96331925075301", characterRepository.get().get(0));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void addUserCharacterCatchesInexistentUser() {
		Response response = route.addUserCharacter("5709b8799a96331925075300", characterRepository.get().get(0));
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void addUserCharacterCatchesInexistentCharacter() {
		Response response = route.addUserCharacter("5709b8799a96331925075301",
				new MarvelCharacter("5709b8799b9a331925075300"));
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void addUserCharacterCatchesDuplicateCharacter() {
		route.addUserCharacter("5709b8799a96331925075301", characterRepository.get().get(0));
		Response response = route.addUserCharacter("5709b8799a96331925075301", characterRepository.get().get(0));
		assertEquals(Status.CONFLICT.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteUserCharacter() {
		Response response = route.deleteUserCharacters("5709b8799a96331925075301");
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteUserCharacterCatchesInexistentCharacter() {
		Response response = route.deleteUserCharacters("5709b8799a96331925075300");
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteUserSingleCharacter() {
		route.addUserCharacter("5709b8799a96331925075301", characterRepository.get().get(0));
		Response response = route.deleteUserSingleCharacter("5709b8799a96331925075301",
				characterRepository.get().get(0).getIdMarvel());
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteUserSingleCharacterCatchesInexistentCharacter() {
		Response response = route.deleteUserSingleCharacter("5709b8799a96331925075301",
				characterRepository.get().get(0).getIdMarvel());
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteUserSingleCharacterCatchesInexistentUser() {
		Response response = route.deleteUserSingleCharacter("5709b8799a96331925075300",
				characterRepository.get().get(0).getIdMarvel());
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void getCharacters() {
		Response response = route.getCharacters();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void getCharacter() {
		Response response = route.getCharacter("1009167");
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void getCharacterInexistentCharacter() {
		Response response = route.getCharacter("1309");
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void getGroups() {
		Response response = route.getGroups();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void getGroup() {
		Response response = route.getGroup("0709b8799a96331925075510");
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void getGroupInexistentCharacter() {
		Response response = route.getGroup("0a09b8799a96331925075510");
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void addGroup() {
		CharacterGroup group = new CharacterGroup("9909b8799a96331925075510");
		group.setName("Test Subject");
		group.addCharacters(characterRepository.get().get(0));
		Response response = route.addGroup(group);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void addGroupTestCatchesInvalidGroup() {
		CharacterGroup group = new CharacterGroup("9909b8799a96331925075510");
		group.setName("Test Subject");
		Response response = route.addGroup(group);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void addGroupTestCatchesDuplicatedGroup() {
		CharacterGroup group = new CharacterGroup("0709b8799a96331925075510");
		group.setName("Test Subject");
		group.addCharacters(characterRepository.get().get(0));
		Response response = route.addGroup(group);
		assertEquals(Status.CONFLICT.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateGroups() {
		List<CharacterGroup> list = new ArrayList<CharacterGroup>();

		CharacterGroup group1 = new CharacterGroup("0709b8799a96331925075510");
		CharacterGroup group2 = new CharacterGroup("1709b8799a96331925075510");
		group1.setName("Test Subject");
		group2.setName("Test Subject");
		group1.addCharacters(characterRepository.get().get(0));
		group2.addCharacters(characterRepository.get().get(0));

		list.add(group1);
		list.add(group2);
		Response response = route.updateGroups(list);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateGroupsCatchesInvalidGroups() {
		List<CharacterGroup> list = new ArrayList<CharacterGroup>();

		CharacterGroup group1 = new CharacterGroup("0709b8799a96331925075510");
		CharacterGroup group2 = new CharacterGroup("1709b8799a96331925075510");
		group1.setName("Test Subject");
		group2.setName("Test Subject");
		group2.addCharacters(characterRepository.get().get(0));
		list.add(group1);
		list.add(group2);
		Response response = route.updateGroups(list);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateGroupsCatchesInexistentGroups() {
		List<CharacterGroup> list = new ArrayList<CharacterGroup>();

		CharacterGroup group1 = new CharacterGroup("a709b8799a96331925075510");
		CharacterGroup group2 = new CharacterGroup("1709b8799a96331925075510");
		group1.setName("Test Subject");
		group2.setName("Test Subject");
		group1.addCharacters(characterRepository.get().get(0));
		group2.addCharacters(characterRepository.get().get(0));

		list.add(group1);
		list.add(group2);
		Response response = route.updateGroups(list);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteAllGroups() {
		Response response = route.deleteAllGroups();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateGroup() {
		CharacterGroup group = new CharacterGroup("0709b8799a96331925075510");
		group.setName("Test Subject");
		group.addCharacters(characterRepository.get().get(0));
		Response response = route.updateGroup(group.getId(), group);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateGroupCatchesInvalidPath() {
		CharacterGroup group = new CharacterGroup("0709b8799a96331925075510");
		group.setName("Test Subject");
		group.addCharacters(characterRepository.get().get(0));
		Response response = route.updateGroup("1709b8799a96331925075510", group);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateGroupCatchesInvalidGroup() {
		CharacterGroup group = new CharacterGroup("0709b8799a96331925075510");
		group.setName("Test Subject");
		Response response = route.updateGroup(group.getId(), group);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateGroupCatchesInexistentGroup() {
		CharacterGroup group = new CharacterGroup("aa09b8799a96331925075510");
		group.setName("Test Subject");
		group.addCharacters(characterRepository.get().get(0));
		Response response = route.updateGroup(group.getId(), group);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateGroupCatchesInexistentPath() {
		CharacterGroup group = new CharacterGroup("0709b8799a96331925075510");
		group.setName("Test Subject");
		group.addCharacters(characterRepository.get().get(0));
		Response response = route.updateGroup("aaa9b8799a96331925075510", group);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteGroup() {
		Response response = route.deleteGroup("0709b8799a96331925075510");
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteGroupCatchesInexistentGroup() {
		Response response = route.deleteGroup("aaa9b8799a96331925075510");
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void groupCharacters() {
		Response response = route.groupCharacters("0709b8799a96331925075510");
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void groupCharactersCatchesInexistentGroup() {
		Response response = route.groupCharacters("aaa9b8799a96331925075510");
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void addGroupCharacter() {
		Response response = route.addGroupCharacter("0709b8799a96331925075510", characterRepository.get().get(1));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void addGroupCharacterCatchesInvalidGroup() {
		Response response = route.addGroupCharacter("0709b8799a96331925075510",
				new MarvelCharacter("0709b8799a96331925075510"));
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void addGroupCharacterCatchesInexistentGroup() {
		Response response = route.addGroupCharacter("aaa9b8799a96331925075510", characterRepository.get().get(1));
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void addGroupCharacterCatchesDuplicateCharacter() {
		Response response = route.addGroupCharacter("0709b8799a96331925075510", characterRepository.get().get(0));
		assertEquals(Status.CONFLICT.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteGroupCharacter() {
		route.addGroupCharacter("0709b8799a96331925075510", characterRepository.get().get(1));
		Response response = route.deleteGroupCharacter("0709b8799a96331925075510",
				characterRepository.get().get(0).getIdMarvel());
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteGroupCharacterCatchesInexistentCharacter() {
		route.addGroupCharacter("0709b8799a96331925075510", characterRepository.get().get(1));
		Response response = route.deleteGroupCharacter("0709b8799a96331925075510", 7098799L);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteGroupCharacterCatchesInvalidGroup() {
		Response response = route.deleteGroupCharacter("0709b8799a96331925075510",
				characterRepository.get().get(0).getIdMarvel());
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteGroupCharacterCatchesInexistentCharacterInGroup() {
		Response response = route.deleteGroupCharacter("0709b8799a96331925075510",
				characterRepository.get().get(1).getIdMarvel());
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void getReports() {
		assertEquals(Status.OK.getStatusCode(), route.getReports().getStatus());
	}
}
