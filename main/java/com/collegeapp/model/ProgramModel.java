package com.collegeapp.model;

public class ProgramModel {
	private int id;
	private String name;
	private String type;
	private String category;

	public ProgramModel() {
	}

	public ProgramModel(int id, String name, String type, String category) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.category = category;
	}

	public ProgramModel(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id= id;
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