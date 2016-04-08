package com.utn.tacs.tacsthree.models;

public abstract class TacsModel {

	private String id = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public abstract Boolean isValid();
}
