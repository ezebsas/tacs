package com.utn.tacs.tacsthree.models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

public abstract class Report extends TacsModel {

	@JsonProperty("name")
	private String name;
	protected ObjectMapper mapper = new ObjectMapper();

	private String getName() {
		return name;
	}

	public void setName(String _name) {
		this.name = _name;
	}

	@JsonProperty("data")
	public abstract String getData();

	@Override
	public void valid() throws InvalidTacsModelException {
		if (this.getName() == null)
			throw new InvalidTacsModelException("invalid name");
	}

}
