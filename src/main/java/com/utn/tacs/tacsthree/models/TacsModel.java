package com.utn.tacs.tacsthree.models;

import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

public abstract class TacsModel {

	private String id = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public abstract void valid() throws InvalidTacsModelException;
}
