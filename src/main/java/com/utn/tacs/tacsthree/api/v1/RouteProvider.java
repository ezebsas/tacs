package com.utn.tacs.tacsthree.api.v1;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.utn.tacs.tacsthree.api.v1.controllers.*;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;
import com.utn.tacs.tacsthree.persistence.UserDAO;
import com.utn.tacs.tacsthree.persistence.mocks.MarvelCharacterTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.UserTestRepository;


@Path("api/v1/")
public class RouteProvider {

	private UserDAO userRepo = new UserTestRepository();
	private MarvelCharacterDAO characRepo = new MarvelCharacterTestRepository();
	private UsersController userController = new UsersController(userRepo, characRepo);
	private MarvelCharactersController characterController = new MarvelCharactersController(characRepo);

	//Lista de Usuarios - Rutas
	
	@GET
	@Path("/users")
	@Produces("application/json")
	public Response users() {
		try {
			return Response.ok(userController.getAllUsers()).build();
		} catch (NullPointerException e) {
			return Response.serverError().build();
		}
	}
	
	@POST
	@Path("/users")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addUser(User newUser) {
		try {
			return Response.ok(userController.createUser(newUser)).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		 
	}
	
	@PUT
	@Path("/users")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateUser(List<User> listaUsuarios) {
		try {
			return Response.ok(userController.updateUsers(listaUsuarios)).build();
		} catch(InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch(InexistentTacsModelException e){
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@DELETE
	@Path("/users")
	public Response deleteUsers() {
		try {
			userController.deleteUsers();
			return Response.ok().build();
		} catch(NullPointerException e){
			return Response.serverError().build();
		}
	}

	//Pedidos por ID - Rutas
	
	@GET
	@Path("/users/{id}")
	@Produces("application/json")
	public Response user(@PathParam("id") String rawId) {
		try {
			return Response.ok(userController.getUser(rawId)).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@PUT
	@Path("/users/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response putUser(@PathParam("id") String rawId, User usuario) {
		try {
			return Response.ok(userController.updateUser(rawId)).build();			
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@DELETE
	@Path("/users/{id}")
	public Response deleteUser(@PathParam("id") String rawId) {
		try{
			userController.deleteUser(rawId);
			return Response.ok().build();
		} catch(InexistentTacsModelException e){
			return Response.status(Status.NOT_FOUND).build();
		} 
	}
	
	//Personajes de Usuario - Rutas
	
	@GET
	@Path("/users/{id}/characters")
	@Produces("application/json")
	public Response getUserCharacters(@PathParam("id") String rawId){
		try{
			userController.getFavoritesOf(rawId);
			return Response.ok().build();
		}catch(InexistentTacsModelException e){
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	//Personajes - Rutas
	
	@GET
	@Path("/characters")
	@Produces("application/json")
	public Response characters() {
		try {
			return Response.ok(characterController.getAllCharacters()).build();
		} catch (NullPointerException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/characters/{id}")
	@Produces("application/json")
	public Response character(@PathParam("id") String rawId) {
		try {
			return Response.ok(characterController.getCharacter(rawId)).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}
