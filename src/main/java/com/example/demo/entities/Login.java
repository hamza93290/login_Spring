package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;


@Entity
public class Login {

	@Id
	@GeneratedValue
	private Integer id;

	private String email;
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
	
	

	public Login() {
	}

	public Login(Integer id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public Login(String email, String password) {
		this.email = email;
		this.password = password;
	}

}
