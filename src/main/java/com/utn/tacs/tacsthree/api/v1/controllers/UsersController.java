package com.utn.tacs.tacsthree.api.v1.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.utn.tacs.tacsthree.models.User;

public class UsersController extends CommonController {

	private List<User> userList = new ArrayList<User>();

	public UsersController() {
		userList.add(new User("1", "Tom"));
		userList.add(new User("2", "Seba"));
		userList.add(new User("3", "Fabi"));
		userList.add(new User("4", "Eze"));
		userList.add(new User("5", "Ramiro"));
		userList.add(new User("6", "Facu"));
	}

	public String getAllUsers() throws JsonGenerationException, JsonMappingException, IOException {
		return toJson(userList);
	}

	public String getUser(Integer id)
			throws NoSuchElementException, JsonGenerationException, JsonMappingException, IOException {
		return toJson(userList.stream().filter(u -> u.getId().equals(id.toString())).findFirst().get());
	}
}
