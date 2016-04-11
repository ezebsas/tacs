package com.utn.tacs.tacsthree.api.v1.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.utn.tacs.tacsthree.models.CharacterGroup;
import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.User;

public class GroupsControllers extends CommonController{
	
	private List<CharacterGroup> grupos = new ArrayList<CharacterGroup>();
	
	public GroupsControllers(){
		
	}
	
	//metodos para /groups
	
//	GET: Devuelve una lista de todos los grupos.
	public List<CharacterGroup> getAllCharactersGroups(){
		return grupos;
	}

//	POST: Crea un nuevo grupo.
	public List<CharacterGroup> postCharactersGroup(CharacterGroup group){
		this.addElem2List(grupos,group);
		return grupos;
	}
	
//	PUT: Actualiza todos los grupos.
	public List<CharacterGroup> putCharactersGroups(List<CharacterGroup> newCharactersGroups){
		this.updateList(grupos, newCharactersGroups);
		return grupos;
	}

//	DELETE: Borra todos los grupos.	
	public void deleteCharacterGroups(){
		grupos.clear();
	}
	
	//metodos para /groups/{:id}

//	GET: Devuelve el grupo cuyo id sea el mismo que el provisto.
	public CharacterGroup getCharactersGroup(Integer _id) throws JsonGenerationException, JsonMappingException, NoSuchElementException, IOException{
		return this.getObjectById(_id, grupos);
	}

//	PUT: Actualiza el grupo cuyo id sea igual al provisto.
	public CharacterGroup putCharacterGroup(Integer id, CharacterGroup newGroup) throws JsonGenerationException, JsonMappingException, NoSuchElementException, IOException	{
		return (CharacterGroup) this.getCharactersGroup(id).actualizarCon(newGroup);
		
	}

//	DELETE: Borra el grupo cuyo id sea igual al provisto.
	public boolean deleteCharacterGroup(String _id) {
		return this.deleteElementInList(_id, grupos);				
	}
	
	// Metodos para /groups/{:id}/characters
	
	//GET: Devuelve los personajes del grupo.
	public List<MarvelCharacter> getCharacters(Integer _id) throws JsonGenerationException, JsonMappingException, NoSuchElementException, IOException{
		return this.getCharactersGroup(_id).getCharacters();
	}
	
	//POST: Agrega un personaje al grupo cuyo id sea el provisto.
	public void postCharacter(Integer _id, MarvelCharacter _charact) throws JsonGenerationException, JsonMappingException, NoSuchElementException, IOException{
		this.getCharactersGroup(_id).addCharacters(_charact);
	}
	
	//DELETE: Remueve todos los personajes del grupo cuyo id sea el provisto.
	public void deleteCharacters(Integer _id) throws JsonGenerationException, JsonMappingException, NoSuchElementException, IOException{
		this.getCharactersGroup(_id).removeAllCharacters();
	}
	


	// metodos para /groups/{:id}/characters/{:id2}:

	//DELETE: Remueve el personaje cuyo id sea :id2 del grupo con id :id.
	public void deleteCharacter(Integer _groupId, String _characterId) throws JsonGenerationException, JsonMappingException, NumberFormatException, NoSuchElementException, IOException{
		this.getCharactersGroup(_groupId).removeFavoriteById(_characterId);
	}

}
