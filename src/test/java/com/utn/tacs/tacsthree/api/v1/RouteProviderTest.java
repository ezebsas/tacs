package com.utn.tacs.tacsthree.api.v1;

import static org.junit.Assert.*;
import javax.ws.rs.core.Response.Status;
import org.junit.Before;
import org.junit.Test;
import com.utn.tacs.tacsthree.models.User;

public class RouteProviderTest {

	public RouteProvider route;

	@Before
	public void setUp(){
		route = new RouteProvider();
	}

	@Test
	public void usersFoundTest(){
		assertEquals(Status.OK.getStatusCode(), route.users().getStatus());
	}

	@Test
	public void userFoundTest(){
		assertEquals(Status.OK.getStatusCode(),  route.user("3").getStatus());
	}
	
	@Test
	public void userNotFoundTest(){
		assertEquals(Status.NOT_FOUND.getStatusCode(),  route.user("666").getStatus());
	}
	
	@Test
	public void userBadRequestTest(){
		assertEquals(Status.BAD_REQUEST.getStatusCode(),  route.user("Tom").getStatus());
	}
	
	@Test
	public void userEntityTest(){
		User userTom = (User) route.user("1").getEntity();
		assertEquals("Tom", userTom.getName());
	}
	
}
