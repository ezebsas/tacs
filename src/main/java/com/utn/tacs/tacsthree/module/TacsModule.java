package com.utn.tacs.tacsthree.module;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.utn.tacs.tacsthree.api.v1.RouteProvider;
import com.utn.tacs.tacsthree.api.v1.controllers.MarvelCharactersController;
import com.utn.tacs.tacsthree.api.v1.controllers.ReportsController;
import com.utn.tacs.tacsthree.connector.MarvelConnector;
import com.utn.tacs.tacsthree.persistence.CharacterGroupDAO;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAOImpl;
import com.utn.tacs.tacsthree.persistence.UserDAO;
import com.utn.tacs.tacsthree.persistence.mocks.CharacterGroupTestRepository;
import com.utn.tacs.tacsthree.persistence.mocks.UserTestRepository;

public class TacsModule implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bind(RouteProvider.class);
		binder.bind(MarvelCharactersController.class);
		binder.bind(MarvelCharacterDAO.class).to(MarvelCharacterDAOImpl.class);
		binder.bind(UserDAO.class).to(UserTestRepository.class);
		binder.bind(CharacterGroupDAO.class).to(CharacterGroupTestRepository.class);
		binder.bind(ReportsController.class);
		binder.bind(MarvelConnector.class);
	}

	@Provides
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		return mapper;
	}

}
