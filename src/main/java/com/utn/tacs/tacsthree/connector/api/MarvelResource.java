package com.utn.tacs.tacsthree.connector.api;

import java.util.List;

public class MarvelResource {

	private List<MarvelItem> items;

	public List<MarvelItem> getItems() {
		return items;
	}

	public void setItems(List<MarvelItem> items) {
		this.items = items;
	}
}
