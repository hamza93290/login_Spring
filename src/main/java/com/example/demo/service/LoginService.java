package com.example.demo.service;

import com.example.demo.dtos.LoginDTO;
import com.example.demo.entities.Login;

public interface LoginService {

	
	Login findUser(String email , String password);
	
	Login save(Login login);
}
