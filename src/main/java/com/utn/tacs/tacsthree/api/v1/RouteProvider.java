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
import com.utn.tacs.tacsthree.exceptions.DuplicateTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;
import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.TacsModel;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.CharacterGroupDAO;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;
import com.utn.tacs.tacsthree.persistence.UserDAO;
import com.utn.tacs.tacsthree.persistence.mocks.MarvelCharacterTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.UserTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.CharacterGroupTestRepository;

@Path("api/v1/")
public class RouteProvider {

	public  UserDAO userRepo = UserTestRepository.getInstance();
	public MarvelCharacterDAO characRepo = MarvelCharacterTestRepository.getInstance();
	public CharacterGroupDAO groupsRepo = CharacterGroupTestRepository.getInstance();
	public UsersController userController = new UsersController(userRepo, characRepo);
	public MarvelCharactersController characterController = new MarvelCharactersController(characRepo);
	public CharacterGroupsController groupsController = new CharacterGroupsController(groupsRepo, characRepo);
	public ReportsController reportsController = new ReportsController();

	@GET
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
	@Path("/users/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateUser(@PathParam("id") String rawId, User usuario) {
		try {
			if (!rawId.equals(usuario.getId()))
				throw new InvalidTacsModelException("user id doesnt match path id");
			return Response.ok(userController.updateUser(usuario)).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
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

	@GET
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
	@Path("/users/{id}/characters")
	public Response deleteUserCharacter(@PathParam("id") String rawId) {
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
	@Path("/users/{id}/characters/{id2}")
	public Response deleteUserSingleCharacter(@PathParam("id") String usrId, @PathParam("id2") String characId) {
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

	@GET
	@Path("/groups")
	@Produces("application/json")
	public Response groups() {
		try {
			return Response.ok(groupsController.getAllGroups()).build();
		} catch (NullPointerException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/groups")
	@Produces("application/json")
	@Consumes("application/json")
	public Response createGroup(CharacterGroup group) {
		try {
			return Response.ok(groupsController.createGroup(group)).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (DuplicateTacsModelException e) {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@PUT
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
	@Path("/groups")
	public Response deleteAllGroups() {
		try {
			groupsController.deleteAllGroups();
			return Response.ok().build();
		} catch (NullPointerException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
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
	@Path("/groups/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response updateGroup(@PathParam("id") String rawId, CharacterGroup newGroup) {
		try {
			if (!rawId.equals(newGroup.getId()))
				throw new InvalidTacsModelException("group id doesnt match path id");
			return Response.ok(groupsController.updateGroup(newGroup)).build();
		} catch (InvalidTacsModelException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/groups/{id}")
	public Response deleteGroup(@PathParam("id") String rawId) {
		try {
			groupsController.deleteGroup(rawId);
			return Response.ok().build();
		} catch (InexistentTacsModelException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
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
	@Path("/groups/{id}/characters")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addCharacter(@PathParam("id") String chId, TacsModel character) {
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
	@Path("/groups/{id1}/characters/{id2}")
	public Response deleteCharacter(@PathParam("id1") String groupId, @PathParam("id2") String characId) {
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
	@Path("/reports")
	@Produces("application/json")
	public Response getReports() {
		try {
			return Response.ok(reportsController.getReports()).build();
		} catch (NullPointerException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
