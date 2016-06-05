package com.utn.tacs.tacsthree.auth;

import com.utn.tacs.tacsthree.exceptions.NotAuthorizedException;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.mocks.UserTestRepository;

import io.jsonwebtoken.Jwts;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {

	private UserTestRepository userRepository = new UserTestRepository();
	private String username = "Facu";
	private String password = "tacs1234";
	private String wrongPassword = "hacker666";
	private Authenticator authenticator = new Authenticator(userRepository);

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
		String response = authenticator.login(username, password);
		String jwt = response.substring("{ \"token\": \"".length(),response.length() - 2).trim();
		System.out.println(jwt);
		assertEquals(Jwts.parser().setSigningKey(authenticator.key).parseClaimsJws(jwt).getBody().getSubject(),
				(username));
	}

	@Test(expected = NotAuthorizedException.class)
	public void failLoginTest() {
		authenticator.login(username, wrongPassword);
		fail();
	}

}