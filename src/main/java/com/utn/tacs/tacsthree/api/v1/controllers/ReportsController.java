package com.utn.tacs.tacsthree.api.v1.controllers;

import java.util.ArrayList;
import java.util.List;

import com.utn.tacs.tacsthree.models.Report;
import com.utn.tacs.tacsthree.models.ReportFavoriteCharacterRanking;
import com.utn.tacs.tacsthree.persistence.MarvelCharacterDAO;
import com.utn.tacs.tacsthree.persistence.UserDAO;

public class ReportsController {

	public List<Report> reports = new ArrayList<Report>();

	public ReportsController(UserDAO users, MarvelCharacterDAO characters) {
		Report mock = new ReportFavoriteCharacterRanking(users, characters);
		mock.setId("aaaab1399a96331925075306");
		reports.add(mock);
	}

	public List<Report> getReports() {
		return reports;
	}

}
