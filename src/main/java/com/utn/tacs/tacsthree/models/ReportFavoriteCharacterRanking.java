package com.utn.tacs.tacsthree.models;

import org.codehaus.jackson.annotate.JsonRawValue;

public class ReportFavoriteCharacterRanking extends Report {

	public ReportFavoriteCharacterRanking() {
		super.setName("Ranking de Personajes favoritos");
	}
	
	@Override
	@JsonRawValue
	public String getData() {
		return "{\"holis\":\"holis\"}";
	}

}
