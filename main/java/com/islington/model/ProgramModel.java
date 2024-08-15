package com.islington.model;

public class ProgramModel {
	private String name;
	private String type;
	private String category;

	public ProgramModel() {
	}

	public ProgramModel(String name, String type, String category) {
		super();
		this.name = name;
		this.type = type;
		this.category = category;
	}
	
	public ProgramModel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}