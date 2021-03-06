package com.utn.tacs.tacsthree.api.v1;

import java.util.Arrays;
import java.util.List;

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

import com.google.inject.Inject;
import com.utn.tacs.tacsthree.api.v1.controllers.CharacterGroupsController;
import com.utn.tacs.tacsthree.api.v1.controllers.MarvelCharactersController;
import com.utn.tacs.tacsthree.api.v1.controllers.ReportsController;
import com.utn.tacs.tacsthree.api.v1.controllers.UsersController;
import com.utn.tacs.tacsthree.exceptions.DuplicateTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;
import com.utn.tacs.tacsthree.exceptions.NotAuthorizedException;
import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.auth.*;

@Path("api/v1/")
public class RouteProvider {

	private UsersController userController;
	private MarvelCharactersController characterController;
	private CharacterGroupsController groupsController;
	private ReportsController reportsController;
	private Authenticator authEngine;

	@Inject
	public RouteProvider(MarvelCharactersController characterController, UsersController userController,
			CharacterGroupsController groupsController, ReportsController reportsController, Authenticator authEngine) {
		this.characterController = characterController;
		this.userController = userController;
		this.groupsController = groupsController;
		this.reportsController = reportsController;
		this.authEngine = authEngine;
	}

	@GET
	@Secured
	@Path("/users")
	@Produces("application/json")
	public Response getUsers() {
		try {
			return Response.ok(userController.getAllUsers()).build();
		} catch (NullPointerException e) {
			return Response.serverError().build();
		}
	}

	@POST
	@Secured
	@Path("/users")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addUser(User newUser) {
		try {
			return Response.ok(userController.createUser(newUser)).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (DuplicateTacsModelException e) {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@PUT
	@Path("/users")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateUsers(List<User> listaUsuarios) {
		try {
			return Response.ok(userController.updateUsers(listaUsuarios)).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/users")
	public Response deleteUsers() {
		try {
			userController.deleteUsers();
			return Response.ok().build();
		} catch (NullPointerException e) {
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/users/{id}")
	@Produces("application/json")
	public Response getUser(@PathParam("id") String rawId) {
		try {
			return Response.ok(userController.getUser(rawId)).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}

	}

	@PUT
	@Secured
	@Path("/users/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateUser(@PathParam("id") String rawId, User usuario) {
		try {
			if (!userController.getUser(rawId).sameModels(usuario))
				throw new InvalidTacsModelException("user id doesnt match path id");
			return Response.ok(userController.updateUser(usuario)).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Secured
	@Path("/users/{id}")
	public Response deleteUser(@PathParam("id") String rawId) {
		try {
			userController.deleteUser(rawId);
			return Response.ok().build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Secured
	@Path("/users/{id}/characters")
	@Produces("application/json")
	public Response getUserCharacters(@PathParam("id") String rawId) {
		try {
			return Response.ok(userController.getCharactersOf(rawId)).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}

	}

	@POST
	@Secured
	@Path("/users/{id}/characters")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addUserCharacter(@PathParam("id") String rawId, MarvelCharacter charac) {
		try {
			return Response.ok(userController.addCharacter(rawId, charac)).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (DuplicateTacsModelException e) {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@DELETE
	@Secured
	@Path("/users/{id}/characters")
	public Response deleteUserCharacters(@PathParam("id") String rawId) {
		try {
			userController.removeCharactersOf(rawId);
			return Response.ok().build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Secured
	@Path("/users/{id}/characters/{id2}")
	public Response deleteUserSingleCharacter(@PathParam("id") String usrId, @PathParam("id2") Long characId) {
		try {
			userController.removeCharacter(usrId, characId);
			return Response.ok().build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Secured
	@Path("/characters")
	@Produces("application/json")
	public Response getCharacters() {
		try {
			return Response.ok(characterController.getAllCharacters()).build();
		} catch (NullPointerException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Secured
	@Path("/characters/{id}")
	@Produces("application/json")
	public Response getCharacter(@PathParam("id") String rawId) {
		try {
			return Response.ok(characterController.getCharacter(Long.valueOf(rawId))).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Secured
	@Path("/groups")
	@Produces("application/json")
	public Response getGroups() {
		try {
			return Response.ok(groupsController.getAllGroups()).build();
		} catch (NullPointerException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Secured
	@Path("/groups")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addGroup(CharacterGroup group) {
		try {
			return Response.ok(groupsController.createGroup(group)).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (DuplicateTacsModelException e) {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@PUT
	@Secured
	@Path("/groups")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateGroups(List<CharacterGroup> cGroups) {
		try {
			return Response.ok(groupsController.updateGroups(cGroups)).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Secured
	@Path("/groups")
	public Response deleteAllGroups() {
		try {
			groupsController.deleteAllGroups(Arrays.asList(userController.userRepository));
			return Response.ok().build();
		} catch (NullPointerException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Secured
	@Path("/groups/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getGroup(@PathParam("id") String rawId) {
		try {
			return Response.ok(groupsController.getGroup(rawId)).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@PUT
	@Secured
	@Path("/groups/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response updateGroup(@PathParam("id") String rawId, CharacterGroup newGroup) {
		try {
			if (!groupsController.getGroup(rawId).sameModels(newGroup))
				throw new InvalidTacsModelException("group id doesnt match path id");
			return Response.ok(groupsController.updateGroup(newGroup)).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Secured
	@Path("/groups/{id}")
	public Response deleteGroup(@PathParam("id") String rawId) {
		try {
			groupsController.deleteGroup(rawId, Arrays.asList(userController.userRepository));
			return Response.ok().build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Secured
	@Path("/groups/{id}/characters")
	@Produces("application/json")
	public Response groupCharacters(@PathParam("id") String rawId) {
		try {
			return Response.ok(groupsController.getCharacters(rawId)).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@Secured
	@Path("/groups/{id}/characters")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addGroupCharacter(@PathParam("id") String chId, MarvelCharacter character) {
		try {
			return Response.ok(groupsController.addCharacter(chId, character)).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (DuplicateTacsModelException e) {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@DELETE
	@Secured
	@Path("/groups/{id1}/characters/{id2}")
	public Response deleteGroupCharacter(@PathParam("id1") String groupId, @PathParam("id2") Long characId) {
		try {
			groupsController.removeCharacter(groupId, characId);
			return Response.ok().build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Secured
	@Path("/reports")
	@Produces("application/json")
	public Response getReports() {
		try {
			return Response.ok(reportsController.getReports()).build();
		} catch (NullPointerException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/login")
	@Consumes("application/x-www-form-urlencoded")
	public Response login(@FormParam("username") String user, @FormParam("password") String pass) {
		try {
			return Response.ok(authEngine.login(user, pass)).build();
		} catch (NotAuthorizedException | InexistentTacsModelException e) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
}
