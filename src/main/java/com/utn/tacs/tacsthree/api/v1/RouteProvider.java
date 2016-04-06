package com.utn.tacs.tacsthree.api.v1;

import java.io.IOException;
import java.util.NoSuchElementException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.utn.tacs.tacsthree.api.v1.controllers.UsersController;

@Path("api/v1/")
public class RouteProvider {

	private UsersController userController = new UsersController();

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
	public Response user(@PathParam("id") Integer id) {
		try {
			return Response.ok(userController.getUser(id)).build();
		} catch (IOException e) {
			return Response.serverError().build();
		} catch (NoSuchElementException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
