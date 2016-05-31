package com.utn.tacs.tacsthree.exceptions;

public class NotAuthorizedException extends TacsModelException {

	private static final long serialVersionUID = 1L;
	
	public NotAuthorizedException(String msj) {
		super(msj);
	}

}