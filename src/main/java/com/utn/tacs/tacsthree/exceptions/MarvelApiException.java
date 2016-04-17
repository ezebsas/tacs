package com.utn.tacs.tacsthree.exceptions;

public class MarvelApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MarvelApiException(String message) {
		super(message);
	}
}
