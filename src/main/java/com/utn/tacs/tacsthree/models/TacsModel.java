package com.utn.tacs.tacsthree.models;

import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public abstract class TacsModel {

	@Id
	private ObjectId id = null;

	public String getId() {
		if (id != null)
			return id.toHexString();
		return null;
	}

	public void setId(String id) {
		this.id = new ObjectId(id);
	}

	public abstract void valid() throws InvalidTacsModelException;
}
