package com.utn.tacs.tacsthree.api.v1.controllers;

import java.util.ArrayList;
import java.util.List;

import com.utn.tacs.tacsthree.models.Report;
import com.utn.tacs.tacsthree.models.ReportFavoriteCharacterRanking;

public class ReportsController {

	public List<Report> reports = new ArrayList<Report>();

	public ReportsController() {
		reports.add(new ReportFavoriteCharacterRanking());
	}

	public List<Report> getReports() {
		return reports;
	}

}
