package com.utn.tacs.tacsthree.api.v1;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

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

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.utn.tacs.tacsthree.api.v1.controllers.*;
import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;
import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
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
	private GroupsControllers groupsController = new GroupsControllers();

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

	//Pedidos de usuario por ID - Rutas
	
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
			usuario = userController.getUser(rawId);
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
	
	@POST	//Checkear este. No se me ocurre como puede estar bien. Deberia buscar el id de personaje... o no?
	@Path("/users/{id}/characters")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addUserFavorite(@PathParam("id") String rawId, MarvelCharacter charac){
		try{
			userController.addFavorite(rawId, charac);
			return Response.ok().build();
		}catch(InvalidTacsModelException e){
			return Response.status(Status.BAD_REQUEST).build();
		}catch(InexistentTacsModelException e){
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@DELETE
	@Path("/users/{id}/characters")
	public Response deleteUserFavorites(@PathParam("id") String rawId){
		try{
			userController.removeFavorites(rawId);
			return Response.ok().build();
		}catch(InexistentTacsModelException e){
			return Response.status(Status.NOT_FOUND).build();
		}catch(InvalidTacsModelException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("/users/{id}/characters/{id2}")
	public Response deleteUserSingleFavorite(@PathParam("id") String usrId, @PathParam("id2") String characId){
		try{
			userController.removeFavorite(usrId, characId);
			return Response.ok().build();
		}catch(InexistentTacsModelException e){
			return Response.status(Status.NOT_FOUND).build();
		}catch(InvalidTacsModelException e){
			return Response.status(Status.BAD_REQUEST).build();
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
	
	
	//Pedidos por Grupo - Rutas
	
	@GET
	@Path("/groups")
	@Produces("application/json")
	public Response groups(){
		try{
			return Response.ok(groupsController.getAllCharactersGroups()).build();
		}catch (NullPointerException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Path("/groups")
	@Produces("application/json")
	@Consumes("application/json")
	public Response createGroup(CharacterGroup group){
		try{
			return Response.ok(groupsController.postCharactersGroup(group)).build();
		}catch(NullPointerException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT
	@Path("/groups")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateGroups(List<CharacterGroup> cGroups){
		try{
			return Response.ok(groupsController.putCharactersGroups(cGroups)).build();
		}catch(NullPointerException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("/groups")
	public Response deleteGroups(){
		try{
			groupsController.deleteCharacterGroups();
			return Response.ok().build();
		}catch (NullPointerException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	//Pedidos para grupos especificos - Rutas
	
	@GET
	@Path("/groups/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getGroup(@PathParam("id") Integer rawId){
		try{
			return Response.ok(groupsController.getCharactersGroup(rawId)).build();
		}catch(JsonGenerationException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}catch(JsonMappingException e){
			return Response.status(Status.BAD_REQUEST).build();
		}catch(NoSuchElementException e){
			return Response.status(Status.NOT_FOUND).build();
		}catch(IOException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT
	@Path("/groups/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response updateGroup(@PathParam("id") Integer rawId, CharacterGroup newGroup){
		try{
			return Response.ok(groupsController.putCharacterGroup(rawId, newGroup)).build();
		}catch(JsonGenerationException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}catch(JsonMappingException e){
			return Response.status(Status.BAD_REQUEST).build();
		}catch(NoSuchElementException e){
			return Response.status(Status.NOT_FOUND).build();
		}catch(IOException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@DELETE
	@Path("/groups/{id}")
	public Response deleteGroup(@PathParam("id") String rawId){
		try{
			groupsController.deleteCharacterGroup(rawId);
			return Response.ok().build();
		}catch(NullPointerException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	//Pedidos para personajes dentro de un grupo - Rutas
	
	@GET
	@Path("/groups/{id}/characters")
	@Produces("application/json")
	public Response groupCharacters(@PathParam("id") Integer rawId){
		try{
			return Response.ok(groupsController.getCharacters(rawId)).build();
		}catch(JsonGenerationException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}catch(JsonMappingException e){
			return Response.status(Status.BAD_REQUEST).build();
		}catch(NoSuchElementException e){
			return Response.status(Status.NOT_FOUND).build();
		}catch(IOException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@POST
	@Path("/groups/{id}/characters")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addCharacter(@PathParam("id") Integer chId, MarvelCharacter character){
		try{
			groupsController.postCharacter(chId, character);
			return Response.ok().build();
		}catch(JsonGenerationException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}catch(JsonMappingException e){
			return Response.status(Status.BAD_REQUEST).build();
		}catch(NoSuchElementException e){
			return Response.status(Status.NOT_FOUND).build();
		}catch(IOException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("/groups/{id}/characters")
	public Response deleteGroupCharacters(@PathParam("id") Integer rawId){
		try{
			groupsController.deleteCharacters(rawId);
			return Response.ok().build();
		}catch(JsonGenerationException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}catch(JsonMappingException e){
			return Response.status(Status.BAD_REQUEST).build();
		}catch(NoSuchElementException e){
			return Response.status(Status.NOT_FOUND).build();
		}catch(IOException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("/groups/{id1}/characters/{id2}")
	public Response deleteCharacter(@PathParam("id1") Integer groupId, @PathParam("id2") String characId){
		try{
			groupsController.deleteCharacter(groupId, characId);
			return Response.ok().build();
		}catch(JsonGenerationException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}catch(JsonMappingException e){
			return Response.status(Status.BAD_REQUEST).build();
		}catch(NoSuchElementException e){
			return Response.status(Status.NOT_FOUND).build();
		}catch(IOException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
