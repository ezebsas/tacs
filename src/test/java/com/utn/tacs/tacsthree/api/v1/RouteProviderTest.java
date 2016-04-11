package com.utn.tacs.tacsthree.api.v1;

import static org.junit.Assert.*;
import javax.ws.rs.core.Response.Status;
import org.junit.Before;
import org.junit.Test;

public class RouteProviderTest {

	public RouteProvider route;

	@Before
	public void setUp() {
		route = new RouteProvider();
	}

	@Test
	public void usersFoundTest() {
		assertEquals(Status.OK.getStatusCode(), route.users().getStatus());
	}

	@Test
	public void userFoundTest() {
		assertEquals(Status.OK.getStatusCode(), route.user("5709b8799a96331925075303").getStatus());
	}

	@Test
	public void userNotFoundTest() {
		assertEquals(Status.NOT_FOUND.getStatusCode(), route.user("1309b8799a96331925075301").getStatus());
	}
}
