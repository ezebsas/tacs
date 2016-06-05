package com.utn.tacs.tacsthree.connectors.api;

import static java.lang.String.format;

public class MarvelImage {

	private static final String STANDARD_AMAZING = "standard_amazing";
	private static final String URL_PATTERN = "%s/%s.%s";
	private String path;
	private String extension;

	public MarvelImage(String path, String extension) {
		this.path = path;
		this.extension = extension;
	}

	public MarvelImage() {
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String imageUrlStandardAmazing() {
		return format(URL_PATTERN, path, STANDARD_AMAZING, extension);
	}

}
