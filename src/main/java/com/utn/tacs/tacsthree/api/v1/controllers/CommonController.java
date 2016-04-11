package com.utn.tacs.tacsthree.api.v1.controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class CommonController {

	private ObjectMapper mapper = new ObjectMapper();

	public String toJson(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		return mapper.writeValueAsString(obj);
	}

	public <T> Object fromJson(String json, Class<T> klass)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, klass);
	}
}
