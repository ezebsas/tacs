package com.utn.tacs.tacsthree.connectors.api;

public class MarvelApiCharacterDataWrapper {

	private Integer code;
	private String status;
	private MarvelApiCharacterDataContainer data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MarvelApiCharacterDataContainer getData() {
		return data;
	}

	public void setData(MarvelApiCharacterDataContainer data) {
		this.data = data;
	}

}
