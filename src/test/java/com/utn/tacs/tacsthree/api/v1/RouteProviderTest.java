package com.utn.tacs.tacsthree.api.v1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.mocks.CharacterGroupTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.MarvelCharacterTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.UserTestRepository;

public class RouteProviderTest {

	public RouteProvider route;

	@Before
	public void setUp() {
		route = new RouteProvider();
		route.userRepo = UserTestRepository.getInstance();
		route.characRepo = MarvelCharacterTestRepository.getInstance();
		route.groupsRepo = CharacterGroupTestRepository.getInstance();
		((UserTestRepository) route.userRepo).restart();
		((MarvelCharacterTestRepository) route.characRepo).restart();
		((CharacterGroupTestRepository) route.groupsRepo).restart();
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
		assertTrue(route.userRepo.get().isEmpty());
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
		Response response = route.addUserCharacter("5709b8799a96331925075301", route.characRepo.get().get(0));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void addUserCharacterCatchesInexistentUser() {
		Response response = route.addUserCharacter("5709b8799a96331925075300", route.characRepo.get().get(0));
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
		route.addUserCharacter("5709b8799a96331925075301", route.characRepo.get().get(0));
		Response response = route.addUserCharacter("5709b8799a96331925075301", route.characRepo.get().get(0));
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
		route.addUserCharacter("5709b8799a96331925075301", route.characRepo.get().get(0));
		Response response = route.deleteUserSingleCharacter("5709b8799a96331925075301",
				route.characRepo.get().get(0).getId());
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteUserSingleCharacterCatchesInexistentCharacter() {
		Response response = route.deleteUserSingleCharacter("5709b8799a96331925075301",
				route.characRepo.get().get(0).getId());
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteUserSingleCharacterCatchesInexistentUser() {
		Response response = route.deleteUserSingleCharacter("5709b8799a96331925075300",
				route.characRepo.get().get(0).getId());
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

}
