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

	// Users paths

	@GET
	@Path("/users")
	@Produces("application/json")
	public Response users() {
		return Response.ok(userController.getAllUsers()).build();
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
	public Response putUser(List<User> listaUsuarios) {
		try {
			return Response.ok(userController.updateUsers(listaUsuarios)).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("/users")
	public Response deleteUsers() {
		userController.deleteUsers();
		return Response.ok().build();
	}

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
	public Response putUser(User usuario) {
		try {
			return Response.ok(userController.updateUser(usuario)).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("/users/{id}")
	public Response deleteUser(@PathParam("id") String rawId) {
		try {
			userController.deleteUser(rawId);
			return Response.ok().build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	// Marvel Character paths

	@GET
	@Path("/characters")
	@Produces("application/json")
	public Response characters() {
		return Response.ok(characterController.getAllCharacters()).build();
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
