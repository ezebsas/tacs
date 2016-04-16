package com.utn.tacs.tacsthree.connector.api;

import java.util.List;

public class MarvelApiCharacterDataContainer {

	private List<MarvelApiCharacter> results;

	public List<MarvelApiCharacter> getResults() {
		return results;
	}

	public void setResults(List<MarvelApiCharacter> results) {
		this.results = results;
	}

}
