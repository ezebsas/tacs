package com.utn.tacs.tacsthree.models;

import org.codehaus.jackson.annotate.JsonRawValue;
import com.utn.tacs.tacsthree.persistence.mongo.*;
import java.util.List;

public class ReportFavoriteCharacterRanking extends Report {
	
	private UserMongoRepository usrRepository;
	private List<User> allUsers;
	
	public ReportFavoriteCharacterRanking() {
		super.setName("Ranking de Personajes favoritos");
	}
	
	@Override
	@JsonRawValue
	public String getData() {
		allUsers = usrRepository.get();
		return "{holis}";
	}

}
