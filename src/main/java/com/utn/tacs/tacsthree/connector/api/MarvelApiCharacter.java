package com.utn.tacs.tacsthree.connector.api;

import java.util.Date;
import java.util.List;

public class MarvelApiCharacter {

	private Long id;
	private String name;
	private String description;
	private Date modified;
	private String resourceURI;
	private List<MarvelUrl> urls;
	private MarvelImage thumbnail;
	private MarvelResource comics;
	private MarvelResource series;
	private MarvelResource stories;
	private MarvelResource events;

	public MarvelResource getComics() {
		return comics;
	}

	public void setComics(MarvelResource comics) {
		this.comics = comics;
	}

	public MarvelResource getSeries() {
		return series;
	}

	public void setSeries(MarvelResource series) {
		this.series = series;
	}

	public MarvelResource getStories() {
		return stories;
	}

	public void setStories(MarvelResource stories) {
		this.stories = stories;
	}

	public MarvelResource getEvents() {
		return events;
	}

	public void setEvents(MarvelResource events) {
		this.events = events;
	}

	public MarvelApiCharacter() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	public List<MarvelUrl> getUrls() {
		return urls;
	}

	public void setUrls(List<MarvelUrl> urls) {
		this.urls = urls;
	}

	public MarvelImage getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(MarvelImage thumbnail) {
		this.thumbnail = thumbnail;
	}

}
