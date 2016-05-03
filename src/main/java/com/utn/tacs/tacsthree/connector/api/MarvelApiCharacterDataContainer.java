package com.utn.tacs.tacsthree.connector.api;

import java.util.List;

public class MarvelApiCharacterDataContainer {

	private Long total;
	private List<MarvelApiCharacter> results;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<MarvelApiCharacter> getResults() {
		return results;
	}

	public void setResults(List<MarvelApiCharacter> results) {
		this.results = results;
	}

}
