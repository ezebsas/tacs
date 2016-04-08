package com.utn.tacs.tacsthree.api.v1.controllers;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.utn.tacs.tacsthree.api.v1.controllers.CommonController;
import com.utn.tacs.tacsthree.models.User;

public class CommonControllerTest {

	public CommonController controller;

	@Before
	public void setUp() throws Exception {
		controller = new CommonController();
	}

	@Test
	@Ignore
	public void toJsonParseCorrectly() {
		try {
			assertEquals("{\"name\":\"Daniel\",\"id\":\"1\"}", controller.toJson(new User("1", "Daniel")));
		} catch (IOException e) {
			fail("Object couldn't be parse to Json Format");
		}
	}

	@Test
	public void fromJsonParseCorrectly() {
		try {
			User testSubject = (User) controller.fromJson("{\"name\":\"Daniel\",\"id\":\"1\"}", User.class);
			assertEquals("Daniel", testSubject.getName());
			assertEquals("1", testSubject.getId());
		} catch (IOException e) {
			fail("Incorrect Json Format");
		}
	}

}
