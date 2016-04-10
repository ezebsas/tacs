package com.utn.tacs.tacsthree.api.v1;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.utn.tacs.tacsthree.api.v1.controllers.CharactersController;
import com.utn.tacs.tacsthree.api.v1.controllers.UsersController;

@Path("api/v1/")
public class RouteProvider {

	private UsersController userController = new UsersController();
	private CharactersController characterController = new CharactersController();

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
