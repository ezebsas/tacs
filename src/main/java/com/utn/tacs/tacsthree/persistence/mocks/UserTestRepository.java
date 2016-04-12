package com.utn.tacs.tacsthree.persistence.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.UserDAO;

public class UserTestRepository implements UserDAO {

	public static UserTestRepository instance = new UserTestRepository();

	public static UserTestRepository getInstance() {
		return instance;
	}

	public void restart() {
		userList.clear();
		userList.add(new User("5709b8799a96331925075301", "Tom"));
		userList.add(new User("5709b8799a96331925075302", "Seba"));
		userList.add(new User("5709b8799a96331925075303", "Fabi"));
		userList.add(new User("5709b8799a96331925075304", "Eze"));
		userList.add(new User("5709b8799a96331925075305", "Ramiro"));
		userList.add(new User("5709b8799a96331925075306", "Facu"));
	}

	private List<User> userList = new ArrayList<User>();

	private UserTestRepository() {
		restart();
	}

	@Override
	public List<User> get() {
		return userList;
	}

	@Override
	public User get(User user) throws InexistentTacsModelException {
		try {
			return userList.stream().filter(o -> o.getId().equals(user.getId())).findFirst().get();
		} catch (NoSuchElementException e) {
			throw new InexistentTacsModelException("get failed");
		}
	}

	@Override
	public void save(User user) {
		try {
			User _user = get(user);
			userList.remove(_user);
		} catch (InexistentTacsModelException e) {
			// Ok! So it doesn't exist, let's create it!
		}
		userList.add(user);
	}

	@Override
	public void delete(User user) throws InexistentTacsModelException {
		try {
			User _user = userList.stream().filter(o -> o.getId().equals(user.getId())).findFirst().get();
			userList.remove(_user);
		} catch (InexistentTacsModelException e) {
			throw new InexistentTacsModelException("delete failed");
		}
	}

	@Override
	public void delete() {
		userList.clear();
	}
}