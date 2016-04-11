package com.utn.tacs.tacsthree.api.v1.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.utn.tacs.tacsthree.models.MarvelCharacter;
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

	//	metodos para /users:
	//
	
	//	GET: Devuelve una lista de todos los usuarios
	public List<User> getAllUsers() throws JsonGenerationException, JsonMappingException, IOException {
		return userList;
	}
	
	//
	//POST: Si creo el nuevo usuario.
	public List<User> addUser(User unUsuario){
		userList.add(new User(unUsuario.getId(), unUsuario.getName()));
		return userList;
	}

	//
	//PUT: 200, actualiza todos los usuarios.
	public User updateUser(User find, User usuario)	{//deprecado, eliminar
		return (User) find.actualizarCon(usuario);
		
	}	
	public User updateUser(Integer id, User usuario) throws JsonGenerationException, JsonMappingException, NoSuchElementException, IOException	{
		return (User) this.getUser(id).actualizarCon(usuario);		
	}

	//
	//DELETE: 200, borra todos los usuarios.
	public void deleteUsers() {
		userList.clear();
	}
	
	//Metodos para /users/{:id}:

	//GET: Devuelve el usuario cuyo id sea igual al provisto.
	public User getUser(Integer id)
			throws NoSuchElementException, JsonGenerationException, JsonMappingException, IOException {
		return this.getObjectById(id, userList);
	}

	//PUT: Actualiza el usuario cuyo id sea igual al provisto.
	public boolean updateUsers(List<User> newList) {
		return this.updateList(userList, newList);
	}

	//DELETE: Borra el usuario cuyo id sea igual al provisto.
	public boolean deleteUser(String _id) {
		return this.deleteElementInList(_id, userList);				
	}



	// Metodos para /users/{:id}/characters:

	//GET: Devuelve la lista de personajes favoritos del usuario cuyo id sea igual al provisto.
	public void getFavorites(String rawId)
			throws JsonGenerationException, JsonMappingException, NumberFormatException, NoSuchElementException, IOException {
		this.getUser(Integer.valueOf(rawId)).getFavorites();
	}

	//POST: Agrega como favorito el personaje dado al usuario cuyo id sea igual al provisto.
	public void addFavorite(String rawId, MarvelCharacter personaje) 
			throws JsonGenerationException, JsonMappingException, NumberFormatException, NoSuchElementException, IOException {
		this.getUser(Integer.valueOf(rawId)).addFavorite(personaje);
	}

	//DELETE: Borra todos los favoritos del usuario cuyo id sea igual al provisto..
	public void removeFavorites(String rawId)
			throws JsonGenerationException, JsonMappingException, NumberFormatException, NoSuchElementException, IOException {
		this.getUser(Integer.valueOf(rawId)).removeFavorites();
	}

	//Metodos para /users/{:id}/characters/{:id2}:

	//DELETE: Borra el favorito con id igual a :id2 del usuario cuyo id sea igual a :id.

	public void removeFavorite(String rawIdU, String rawIdC)
			throws JsonGenerationException, JsonMappingException, NumberFormatException, NoSuchElementException, IOException {
		this.getUser(Integer.valueOf(rawIdU)).removeFavoriteById(rawIdC);
	}
	
	
	

	
	
}
