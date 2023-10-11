package com.example.demo.dtos;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;

public class LoginDTO {

	private Integer id;

	@NotEmpty(message = "Le mail doit être renseigné.")
	private String email;

	@NotEmpty(message = "Le password doit être renseigné.")
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginDTO(Integer id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

}
