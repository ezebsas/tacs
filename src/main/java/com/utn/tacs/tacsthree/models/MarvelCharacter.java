package com.utn.tacs.tacsthree.models;

import java.time.LocalDate;
import java.util.List;

import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

public class MarvelCharacter extends TacsModel {
	// http://developer.marvel.com/documentation/entity_types

	private Long characterId;
	private String name = null; // The name of the character.
	private String description = null; // Short bio or description of the
										// character.
	private LocalDate modified; // The date the resource was most recently
								// modified.
	private String resourceURI; // The canonical URL identifier for this
								// resource.
	private List<String> urls; // A set of public web site URLs for the
								// resource.
	private String thumbnailUrl; // The representative image for this character.
	private List<String> comics; // A resource list containing comics which
									// feature this character.
	private List<String> stories; // A resource list of stories in which this
									// character appears.
	private List<String> events; // A resource list of events in which this
									// character appears.
	private List<String> series; // A resource list of series in which this
									// character appears.

	public MarvelCharacter() {

	}

	public MarvelCharacter(String _id) {
		this.setId(_id);
	}

	public MarvelCharacter(String _id, String nombre, String descripcion) {
		this.setId(_id);
		this.setName(nombre);
		this.setDescription(descripcion);

	}

	public Long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Long characterId) {
		this.characterId = characterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getModified() {
		return modified;
	}

	public void setModified(LocalDate modified) {
		this.modified = modified;
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public List<String> getComics() {
		return comics;
	}

	public void setComics(List<String> comics) {
		this.comics = comics;
	}

	public List<String> getStories() {
		return stories;
	}

	public void setStories(List<String> stories) {
		this.stories = stories;
	}

	public List<String> getEvents() {
		return events;
	}

	public void setEvents(List<String> events) {
		this.events = events;
	}

	public List<String> getSeries() {
		return series;
	}

	public void setSeries(List<String> series) {
		this.series = series;
	}

	@Override
	public void valid() {
		if (this.getId() == null)
			throw new InvalidTacsModelException("invalid id");
	}

}
