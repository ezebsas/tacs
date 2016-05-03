package com.utn.tacs.tacsthree.models;

import org.codehaus.jackson.annotate.JsonProperty;

import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

public abstract class Report extends TacsModel {
		
	@JsonProperty("name")
	private String name;

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
		if (this.getId() == null)
			throw new InvalidTacsModelException("invalid id");
		if (this.getName() == null)
			throw new InvalidTacsModelException("invalid name");
	}

}
