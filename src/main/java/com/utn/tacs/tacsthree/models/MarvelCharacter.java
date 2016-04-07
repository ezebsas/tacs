package com.utn.tacs.tacsthree.models;


public class MarvelCharacter implements TacsModel {

	private String id = null;
	// TODO: Generate attributes like the ones in the marvel api
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.utn.tacs.tacsthree.models.TacsModel#isValid()
	 */
	@Override
	public Boolean isValid() {
		return id != null;
	}

}
