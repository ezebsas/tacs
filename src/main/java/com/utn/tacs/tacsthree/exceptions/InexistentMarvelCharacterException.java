package com.utn.tacs.tacsthree.exceptions;

public class InexistentMarvelCharacterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InexistentMarvelCharacterException(String message) {
		super(message);
	}

}
