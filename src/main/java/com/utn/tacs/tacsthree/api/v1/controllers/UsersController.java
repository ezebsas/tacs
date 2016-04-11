package com.utn.tacs.tacsthree.api.v1.controllers;

import java.util.List;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;
import com.utn.tacs.tacsthree.persistence.UserDAO;

public class UsersController {
	private UserDAO userRepository;
	private MarvelCharacterDAO characterRepository;

	public UsersController(UserDAO _users, MarvelCharacterDAO _characters) {
		this.userRepository = _users;
	}

	public List<User> getAllUsers() {
		return userRepository.get();
	}

	public User getUser(String id) throws InexistentTacsModelException {
		return userRepository.get(new User(id));
	}

	public User createUser(User user) throws InvalidTacsModelException {
		user.valid();
		userRepository.save(user);
		return user;
	}

	public List<User> updateUsers(List<User> userList) throws InexistentTacsModelException, InvalidTacsModelException {
		userList.forEach(u -> updateUser(u));
		return userList;
	}

	public User updateUser(User user) throws InexistentTacsModelException, InvalidTacsModelException {
		user.valid();
		userRepository.get(user);
		userRepository.save(user);
		return user;
	}

	public void deleteUsers() {
		userRepository.delete();
	}

	public void deleteUser(String _id) throws InexistentTacsModelException {
		userRepository.delete(new User(_id));
	}

	// Favorite Characters

	public void getFavoritesOf(String _id) throws InexistentTacsModelException {
		getUser(_id).getFavorites();
	}

	public void addFavorite(String _id, MarvelCharacter _character)
			throws InexistentTacsModelException, InvalidTacsModelException {
		User user = getUser(_id);
		user.addFavorite(_character);
		updateUser(user);
	}

	public void removeFavorites(String _id) throws InexistentTacsModelException, InvalidTacsModelException {
		User user = getUser(_id);
		user.removeFavorites();
		updateUser(user);
	}

	public void removeFavorite(String userId, String favId)
			throws InexistentTacsModelException, InvalidTacsModelException {
		User user = getUser(userId);
		MarvelCharacter fav = characterRepository.get(new MarvelCharacter(favId));
		user.removeFavorite(fav);
		updateUser(user);
	}
}
