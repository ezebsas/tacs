package com.utn.tacs.tacsthree.persistence.mocks;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.models.TacsModel;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.UserDAO;

public class UserTestRepository implements UserDAO {

	public List<User> userList = new ArrayList<User>();

	@Inject
	public UserTestRepository() {
		restart();
	}

	public void restart() {
		userList.clear();
		save(new User("1309b8799a96331925075301", "Tom"));
		save(new User("1309b8799a96331925075301", "Seba"));
		save(new User("1309b8799a96331925075301", "Fabi"));
		save(new User("1309b8799a96331925075301", "Eze"));
		save(new User("1309b8799a96331925075301", "Ramiro"));
		save(new User("1309b8799a96331925075301", "Facu"));
	}

	@Override
	public List<User> get() {
		return userList;
	}

	@Override
	public User get(User user) throws InexistentTacsModelException {
		for (User _user : userList)
			if (_user.sameModels(user))
				return _user;
		throw new InexistentTacsModelException("get failed");
	}
        
        @Override
        public User get(String username) throws InexistentTacsModelException{
            for (User _user: userList)
                if(_user.getName().equals(username))
                    return _user;
            throw new InexistentTacsModelException("get user failed");
        }

	@Override
	public void save(User user) {
		try {
			User _user = get(user);
			userList.remove(_user);
		} catch (InexistentTacsModelException e) {
			// Ok! So it doesn't exist, let's create it!
			user.generateNewId();
		}
		userList.add(user);
	}

	@Override
	public void delete(User user) throws InexistentTacsModelException {
		try {
			User _user = get(user);
			userList.remove(_user);
		} catch (InexistentTacsModelException e) {
			throw new InexistentTacsModelException("delete failed");
		}
	}

	@Override
	public void delete() {
		userList.clear();
	}

	@Override
	public void tellAboutElimination(List<? extends TacsModel> observees) {
		for (User user : userList)
			for (TacsModel character : observees)
				try {
					user.removeGroup((CharacterGroup) character);
				} catch (InexistentTacsModelException e) {
					// It's Ok, if that group wasnt crated by this user ;)
				}
	}
}
