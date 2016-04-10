package com.utn.tacs.tacsthree.models;

import java.time.LocalDate;
import java.util.List;

public class BuilderCharacter {

	public MarvelCharacter personaje = new MarvelCharacter();

	public BuilderCharacter() {
	}
	
	public MarvelCharacter build() {
		return personaje;
	}
	
	public MarvelCharacter buildConValidacion() {
		this.validarDatosObligatorios();
		return personaje;
	}
	
	// *********************************************
	// Validar datos
	// *********************************************
	
	private BuilderCharacter validarDatosObligatorios() { // complexion, nombre, fechaNacimiento, rutina
		if (!personaje.isValid()) {
			throw new BuilderCharacterException("Datos incorrectos");
		}
		return this;
	}
	
	// *********************************************
	// Métodos de configuración
	// *********************************************
	
	public BuilderCharacter definirNombre(String nombre) {
		personaje.setName(nombre);
		return this;
	}
	
	public BuilderCharacter definirDescripcion(String descripcion) {
		personaje.setDescription(descripcion);
		return this;
	}
	
	public BuilderCharacter definirModificacion(LocalDate fecha) {
		personaje.setModified(fecha);
		return this;
	}
	
    public BuilderCharacter definirRecursoURI(String recursoURI) {
		personaje.setResourceURI(recursoURI);
		return this;
	}

    public BuilderCharacter definirUrls(List<String> urls) {
		personaje.setUrls(urls);
		return this;
	}

    public BuilderCharacter definirThumbnailUrl(String thumbnailUrl) {
		personaje.setThumbnailUrl(thumbnailUrl);
		return this;
	}

    public BuilderCharacter definirComics(List<String> comics) {
		personaje.setComics(comics);
		return this;
	}

    public BuilderCharacter definirStories(List<String> stories) {
		personaje.setComics(stories);
		return this;
	}

    public BuilderCharacter definirEvents(List<String> events) {
		personaje.setEvents(events);
		return this;
	}

    public BuilderCharacter definirSeries(List<String> series) {
		personaje.setEvents(series);
		return this;
	}
    
    public BuilderCharacter definirId(String _id) {
		personaje.setId(_id);
		return this;
	}
}
