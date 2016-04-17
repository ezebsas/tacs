package com.utn.tacs.tacsthree.models;

import static org.junit.Assert.*;
import org.junit.Test;

import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

public class ReportTest {

	@Test
	public void createReportRequiredValues() {
		Report report = new ReportFavoriteCharacterRanking();
		report.setId("5709b8799a96331925075306");
		report.valid();
	}

	@Test
	public void createReportWithoutId() {
		Report report = new ReportFavoriteCharacterRanking();
		try {
			report.valid();
		} catch (InvalidTacsModelException e) {
			assertEquals("invalid id", e.getMessage());
		}
	}
}
