package com.utn.tacs.tacsthree.api.v1.controllers;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.utn.tacs.tacsthree.models.MarvelCharacter;
import com.utn.tacs.tacsthree.models.TacsModel;
import com.utn.tacs.tacsthree.models.User;

public class CommonController {

	private ObjectMapper mapper = new ObjectMapper();

	public String toJson(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		return mapper.writeValueAsString(obj);
	}

	public <T> Object fromJson(String json, Class<T> klass)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, klass);
	}

	public <E> E getObjectById(Integer id, List<E> listOrder)
			throws NoSuchElementException, JsonGenerationException, JsonMappingException, IOException {
		return listOrder.stream().filter(o -> ((TacsModel) o).getId().equals(id.toString())).findFirst().get();
	}
	
	public <E> void addElem2List(List<E> lista, E element){
		lista.add(element);
	}
	
	public <E> boolean deleteElementInList(String _id, List<E> lista) {
		return lista.removeIf(u -> ((TacsModel) u).getId().equals(_id));
	}
	
	public <E> boolean updateList(List<E> original, List<E> newList) {
		if(this.isNewListInOriginal(original, newList)){
			this.updateOriginalWithNew(original,newList);		
			return true;
		}
		else
			return false;
	}
	
	public <E> boolean isNewListInOriginal(List<E> original, List<E> newList){
		return newList.stream().allMatch(el -> this.isInList(original,el));
	}
	
	public <E> boolean isInList(List<E> list, E element){
		return list.stream().anyMatch(e -> ((TacsModel) e).getId().equals( ((TacsModel) element).getId()) );
	}
	
	public <E> void updateOriginalWithNew(List<E> original, List<E> newList){
		newList.forEach(e -> {
	        original.forEach(o -> {
	            if (((TacsModel) o).getId().equals(((TacsModel) e).getId())) {
	                ((TacsModel) o).actualizarCon(((TacsModel) e));
	            }
	        });
	    });
	}
}
