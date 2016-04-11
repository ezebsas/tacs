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

	public List<User> getAllUsers() throws JsonGenerationException, JsonMappingException, IOException {
		return userList;
	}

	public User getUser(Integer id)
			throws NoSuchElementException, JsonGenerationException, JsonMappingException, IOException {
		return userList.stream().filter(u -> u.getId().equals(id.toString())).findFirst().get();
	}
	
	public User updateUser(User find, User usuario)	{
		return find.actualizarCon(usuario);
		
	}
	
	public List<User> addUser(User unUsuario){
		userList.add(new User(unUsuario.getId(), unUsuario.getName()));
		return userList;
	}

	public void deleteUsers() {
		userList.clear();
	}
	
	public boolean deleteUser(String _id) {
		return userList.removeIf(u -> u.getId().equals(_id));
	}

	public boolean updateUsers(List<User> newList) {
		if(this.listaIncluidaEnUsuarios(newList)){
			this.actualizarListaUsuarios(newList);		
			return true;
		}
		else
			return false;
	}
	
	public boolean listaIncluidaEnUsuarios(List<User> lista){
		return lista.stream().allMatch(u -> this.estaEnLaLista(u));
	}
	
	public boolean estaEnLaLista(User usuario){
		return userList.stream().anyMatch(u -> u.getId().equals( usuario.getId()) );
	}
	
	public void actualizarListaUsuarios(List<User> lista){
		lista.forEach(u -> {
	        userList.forEach(original -> {
	            if (original.getId().equals(u.getId())) {
	                original.actualizarCon(u);
	            }
	        });
	    });
	}

	

	
	
}
