package com.utn.tacs.tacsthree.api.v1;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.utn.tacs.tacsthree.api.v1.controllers.CharactersController;
import com.utn.tacs.tacsthree.api.v1.controllers.UsersController;
import com.utn.tacs.tacsthree.models.User;

@Path("api/v1/")
public class RouteProvider {

	private static UsersController userController = new UsersController();
	private static CharactersController characterController = new CharactersController();

	//Routeo de todos los usuarios
	
	@GET
	@Path("/users")
	@Produces("application/json")
	public Response users() {
		try {
			return Response.ok(userController.getAllUsers()).build();
		} catch (IOException e) {
			return Response.serverError().build();
		}
	}
	
	@POST
	@Path("/users")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addUser(User newUser) {
		return Response.ok(userController.addUser(newUser)).build();
		 
	}
	
	@PUT
	@Path("/users")
	@Consumes("application/json")
	public Response putUser(List<User> listaUsuarios) {
		if(userController.updateUsers(listaUsuarios))
			return Response.ok().build();
		else
			return Response.status(400).build();
		 
	}
	
	@DELETE
	@Path("/users")
	public Response deleteUsers() {
		userController.deleteUsers();
		return Response.ok().build();	
	}

	//Routeo de usuarios especificos por id
	
	@GET
	@Path("/users/{id}")
	@Produces("application/json")
	public Response user(@PathParam("id") String rawId) {
		try {
			return Response.ok(userController.getUser(Integer.valueOf(rawId))).build();
		} catch (NumberFormatException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (IOException e) {
			return Response.serverError().build();
		} catch (NoSuchElementException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@PUT
	@Path("/users/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response putUser(@PathParam("id") String rawId, User usuario) {
		User find;
		try {
			find = userController.getUser(Integer.valueOf(rawId));			
		} catch (NumberFormatException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (IOException e) {
			return Response.serverError().build();
		} catch (NoSuchElementException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(userController.updateUser(find, usuario)).build();
	}
	
	@DELETE
	@Path("/users/{id}")
	public Response deleteUser(@PathParam("id") String rawId) {
		if (userController.deleteUser(rawId)){
			return Response.ok().build();
		} 
		else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	//Routeo de personajes de usuarios especificos
	
//	@POST
//	@Path("/users/{id}/characters")
//	@Consumes("application/json")
//	@Produces("application/json")
//	public Response addFavoriteCharacterOfUser(@PathParam("id") String rawId, Character personaje) {
//		return Response.ok(userController.addFavoriteCharacterOfUser(rawId, personaje)).build();
//		 
//	}
	
	
	//Routeo de personajes
	
	@GET
	@Path("/characters")
	@Produces("application/json")
	public Response characters() {
		try {
			return Response.ok(characterController.getAllCharacters()).build();
		} catch (IOException e) {
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/characters/{id}")
	@Produces("application/json")
	public Response character(@PathParam("id") String rawId) {
		try {
			return Response.ok(characterController.getCharacter(Integer.valueOf(rawId))).build();
		} catch (NumberFormatException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (IOException e) {
			return Response.serverError().build();
		} catch (NoSuchElementException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}
