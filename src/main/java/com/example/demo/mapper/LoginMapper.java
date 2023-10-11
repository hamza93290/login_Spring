package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dtos.LoginDTO;
import com.example.demo.entities.Login;

@Component
public class LoginMapper {

	public LoginDTO toDto(Login login){
		
		return new LoginDTO(login.getId(),login.getEmail(),login.getPassword());
	}
	
    public Login toEntity(LoginDTO loginDTO){
		
		return new Login(loginDTO.getId(),loginDTO.getEmail(),loginDTO.getPassword());
	}
}
