package com.college.model;

import java.time.LocalDate;

public class StudentModel {

	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private LocalDate dob;
	private String gender;
	private String email;
	private String number;
	private String password;
	private ProgramModel program;
	private String imageUrl;

	public StudentModel() {
	}

	public StudentModel(String username, String pasword) {
		this.userName = username;
		this.password = pasword;
	}

	public StudentModel(int id, String firstName, String lastName, String userName, LocalDate dob, String gender,
			String email, String number, String password, ProgramModel program, String imageUrl) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.number = number;
		this.password = password;
		this.program = program;
		this.imageUrl = imageUrl;
	}

	public StudentModel(String firstName, String lastName, String userName, LocalDate dob, String gender, String email,
			String number, String password, ProgramModel program, String imageUrl) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.number = number;
		this.password = password;
		this.program = program;
		this.imageUrl = imageUrl;
	}

	public StudentModel(int id, String firstName, String lastName, ProgramModel program, String email, String number) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.program = program;
		this.email = email;
		this.number = number;
	}
	
	public StudentModel(int id, String firstName, String lastName, String email, String number) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProgramModel getProgram() {
		return program;
	}

	public void setProgram(ProgramModel program) {
		this.program = program;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}