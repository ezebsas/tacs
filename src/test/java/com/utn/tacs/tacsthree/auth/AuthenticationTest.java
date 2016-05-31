package com.utn.tacs.tacsthree.auth;

import com.utn.tacs.tacsthree.api.v1.RouteProvider;
import com.utn.tacs.tacsthree.api.v1.controllers.CharacterGroupsController;
import com.utn.tacs.tacsthree.api.v1.controllers.MarvelCharactersController;
import com.utn.tacs.tacsthree.api.v1.controllers.ReportsController;
import com.utn.tacs.tacsthree.api.v1.controllers.UsersController;
import com.utn.tacs.tacsthree.exceptions.NotAuthorizedException;
import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.mocks.CharacterGroupTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.MarvelCharacterTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.UserTestRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;
import java.util.Date;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {

	private UserTestRepository userRepository = new UserTestRepository();
	private RouteProvider route;
	private String header;
	private String payload;
	private String signature;
	private String username = "Facu";
	private String password = "tacs1234";
	private String wrongPassword = "hacker666";
	private Authenticator authenticator = new Authenticator(userRepository);
	private Key key = MacProvider.generateKey();

	@Before
	public void setUp() {
		userRepository.delete();
		userRepository.userList.add(new User("5709b8799a96331925075301", "Tom", "tacs1234"));
		userRepository.userList.add(new User("5709b8799a96331925075302", "Seba", "tacs1234"));
		userRepository.userList.add(new User("5709b8799a96331925075303", "Fabi", "tacs1234"));
		userRepository.userList.add(new User("5709b8799a96331925075304", "Eze", "tacs1234"));
		userRepository.userList.add(new User("5709b8799a96331925075305", "Ramiro", "tacs1234"));
		userRepository.userList.add(new User("5709b8799a96331925075306", "Facu", "tacs1234"));
	}

	@Test
	public void successLoginTest() {

		String jwt = authenticator.login(username, password);

		assertEquals(Jwts.parser().setSigningKey(authenticator.key).parseClaimsJws(jwt).getBody().getSubject(),
				(username));
	}

	@Test(expected = NotAuthorizedException.class)
	public void failLoginTest() {

		String jwt = authenticator.login(username, wrongPassword);

		fail();
	}

}