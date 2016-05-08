package com.utn.tacs.tacsthree.exceptions;

public class InexistentTacsModelException extends TacsModelException {

	private static final long serialVersionUID = 1L;

	public InexistentTacsModelException(String msj) {
		super(msj);
	}

	public InexistentTacsModelException(String msj, Throwable cause) {
		super(msj, cause);
	}
}
