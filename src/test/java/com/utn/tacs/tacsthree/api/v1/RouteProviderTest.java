package com.utn.tacs.tacsthree.api.v1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.Before;
import org.junit.Test;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
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
		try {
			route.userRepo.get(new User("5709b8799a96331925075300"));
		} catch (InexistentTacsModelException e) {
			fail("User isn't in the test repo");
		}
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
		try {
			assertEquals("Test Subject 1", route.userRepo.get(new User("5709b8799a96331925075301")).getName());
			assertEquals("Test Subject 2", route.userRepo.get(new User("5709b8799a96331925075302")).getName());
		} catch (InexistentTacsModelException e) {
			fail("Users aren't in the test repo");
		}
	}

	@Test
	public void updateUsersCatchesInvalidUser() {
		List<User> list = new ArrayList<User>();
		list.add(new User("5709b8799a96331925075301"));
		list.add(new User("5709b8799a96331925075302", "Test Subject 2"));
		Response response = route.updateUsers(list);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		try {
			assertNotEquals(null, route.userRepo.get(new User("5709b8799a96331925075301")).getName());
			assertNotEquals("Test Subject 2", route.userRepo.get(new User("5709b8799a96331925075302")).getName());
		} catch (InexistentTacsModelException e) {
			fail("Users got updated");
		}
	}

	@Test
	public void updateUsersCatchesInexistentUser() {
		List<User> list = new ArrayList<User>();
		list.add(new User("5709b8799a96331925075300", "Test Subject 1"));
		list.add(new User("5709b8799a96331925075302", "Test Subject 2"));
		Response response = route.updateUsers(list);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
		try {
			assertNotEquals("Test Subject 2", route.userRepo.get(new User("5709b8799a96331925075302")).getName());
			route.userRepo.get(new User("5709b8799a96331925075300"));
			fail("Updated User got created");
		} catch (InexistentTacsModelException e) {
		}
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
		assertEquals("5709b8799a96331925075301", ((User) response.getEntity()).getId());
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
		try {
			route.userRepo.get(new User("5709b8799a96331925075301"));
			fail("User didn't get deleted");
		} catch (InexistentTacsModelException e) {
		}
	}

	@Test
	public void deleteUserCatchesInexistentUser() {
		Integer previousUsersAmount = route.userRepo.get().size();
		Response response = route.deleteUser("5709b8799a96331925075300");
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
		assertEquals(previousUsersAmount, route.userRepo.get().size(), 0);
	}
}
