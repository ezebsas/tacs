package com.utn.tacs.tacsthree.persistence;

import java.util.List;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.User;

public interface UserDAO extends ObserverDAO {

	List<User> get();

	User get(User user) throws InexistentTacsModelException;
        
        User get(String username) throws InexistentTacsModelException;

	void save(User user);

	void delete(User user) throws InexistentTacsModelException;

	void delete();
}
