package com.utn.tacs.tacsthree.models;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonRawValue;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Maps;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;
import com.utn.tacs.tacsthree.persistence.UserDAO;

public class ReportFavoriteCharacterRanking extends Report {

	private MarvelCharacterDAO characters;
	private UserDAO users;

	public ReportFavoriteCharacterRanking(UserDAO users, MarvelCharacterDAO characters) {
		super.setName("Ranking de Personajes favoritos");
		this.users = users;
		this.characters = characters;
	}

	@Override
	@JsonRawValue
	public String getData() {
		final Map<String, Integer> ranking = Maps.newHashMap();
		List<MarvelCharacter> result_list = characters.get();
		for (MarvelCharacter _character : result_list)
			ranking.put(_character.getId(), 0);
		for (User _user : users.get())
			for (MarvelCharacter _character : _user.getCharacters())
				if (ranking.get(_character.getId()) != null)
					ranking.replace(_character.getId(), ranking.get(_character.getId()) + 1);

		Collections.sort(result_list, new Comparator<MarvelCharacter>() {
			@Override
			public int compare(MarvelCharacter char1, MarvelCharacter char2) {
				return ranking.get(char2.getId()) <= ranking.get(char1.getId()) ? -1 : 1;
			}
		});
		try {
			return mapper.writeValueAsString(FluentIterable.from(result_list).limit(10).toList());
		} catch (IOException e) {
			return "";
		}
	}

}
