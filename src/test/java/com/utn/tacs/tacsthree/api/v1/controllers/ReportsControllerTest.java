package com.utn.tacs.tacsthree.api.v1.controllers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.utn.tacs.tacsthree.models.Report;

public class ReportsControllerTest {

	private ReportsController controller;

	@Before
	public void setUp() {
		controller = new ReportsController(null, null);
	}

	@Test
	public void getReports() {
		List<Report> result = controller.getReports();
		assertEquals(controller.getReports().size(), result.size());
	}

}
