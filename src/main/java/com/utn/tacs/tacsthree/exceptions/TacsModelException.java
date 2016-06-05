package com.utn.tacs.tacsthree.exceptions;

public class TacsModelException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TacsModelException(String msj) {
		super(msj);
	}

	public TacsModelException(String msj, Throwable cause) {
		super(msj, cause);
	}
}
