package com.utn.tacs.tacsthree.persistence;

import java.util.List;
import com.utn.tacs.tacsthree.models.TacsModel;

public interface ObserverDAO {
	public void tellAboutElimination(List<? extends TacsModel> observees);
}