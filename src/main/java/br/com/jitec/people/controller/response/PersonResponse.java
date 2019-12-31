package br.com.jitec.people.controller.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PersonResponse {

	private String publicId;
	private String name;
	private Integer age;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	private String email;

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
