package br.com.jitec.people.controller.request;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PersonRequest {

	@NotNull(message = "Name should be informed")
	@Size(min = 2, message = "Name should have at least 2 characters")
	private String name;

	@NotNull(message = "Birth date should be informed")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	@Email
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
