package com.utn.tacs.tacsthree.models;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Date;
import java.util.List;

import com.utn.tacs.tacsthree.exceptions.InvalidTacsModelException;

public class MarvelCharacter extends TacsModel {
	// http://developer.marvel.com/documentation/entity_types

	private Long idMarvel;
	private String name = null;
	private String description = null;
	private Date modified;
	private String resourceURI;
	private List<String> urls = newArrayList();
	private String thumbnailUrl;
	private List<String> comics = newArrayList();
	private List<String> stories = newArrayList();
	private List<String> events = newArrayList();
	private List<String> series = newArrayList();

	public MarvelCharacter() {

	}

	public MarvelCharacter(String _id) {
		this.setId(_id);
	}

	public MarvelCharacter(Long idMarvel) {
		this.idMarvel = idMarvel;
	}

	public MarvelCharacter(String _id, Long idMarvel, String nombre, String descripcion) {
		this.setId(_id);
		this.setIdMarvel(idMarvel);
		this.setName(nombre);
		this.setDescription(descripcion);

	}

	public Long getIdMarvel() {
		return idMarvel;
	}

	public void setIdMarvel(Long idMarvel) {
		this.idMarvel = idMarvel;
	}

	/**
	 * @return The name of the character
	 */
	public String getName() {
		return name;
	}

	/**
	 * The name of the character
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return Short bio or description of the character
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Short bio or description of the character
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return The date the resource was most recently modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * The date the resource was most recently modified
	 * 
	 * @param modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * 
	 * @return The canonical URL identifier for this resource
	 */
	public String getResourceURI() {
		return resourceURI;
	}

	/**
	 * The canonical URL identifier for this resource
	 * 
	 * @param resourceURI
	 */
	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	/**
	 * 
	 * @return A set of public web site URLs for the resource
	 */
	public List<String> getUrls() {
		return urls;
	}

	/**
	 * A set of public web site URLs for the resource
	 * 
	 * @param urls
	 */
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	/**
	 * 
	 * @return The representative image for this character
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	/**
	 * The representative image for this character
	 * 
	 * @param thumbnailUrl
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	/**
	 * 
	 * @return List containing comics which feature this character
	 */
	public List<String> getComics() {
		return comics;
	}

	/**
	 * List containing comics which feature this character
	 * 
	 * @param comics
	 */
	public void setComics(List<String> comics) {
		this.comics = comics;
	}

	/**
	 * 
	 * @return List of stories in which this character appears.
	 */
	public List<String> getStories() {
		return stories;
	}

	/**
	 * List of stories in which this character appears.
	 * 
	 * @param stories
	 */
	public void setStories(List<String> stories) {
		this.stories = stories;
	}

	/**
	 * 
	 * @return List of events in which this character appears
	 */
	public List<String> getEvents() {
		return events;
	}

	/**
	 * List of events in which this character appears
	 * 
	 * @param events
	 */
	public void setEvents(List<String> events) {
		this.events = events;
	}

	/**
	 * 
	 * @return List of series in which this character appears
	 */
	public List<String> getSeries() {
		return series;
	}

	/**
	 * List of series in which this character appears
	 * 
	 * @param series
	 */
	public void setSeries(List<String> series) {
		this.series = series;
	}

	@Override
	public void valid() {
		if (this.getId() == null)
			throw new InvalidTacsModelException("invalid id");
	}

	@Override
	public Boolean sameModels(TacsModel _model) {
		MarvelCharacter character = (MarvelCharacter) _model;
		return this.getIdMarvel().equals(character.getIdMarvel());
	}
}
