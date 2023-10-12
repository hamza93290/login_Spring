package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dtos.LoginDTO;
import com.example.demo.entities.Login;

public interface LoginService {

	
	Login findUser(String email , String password);
	
	
	//UserDetailsService findUserByEmail() ;
	
	Login save(Login login);
}
