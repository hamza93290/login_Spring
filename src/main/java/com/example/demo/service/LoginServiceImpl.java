package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.LoginDTO;
import com.example.demo.entities.Login;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginServiceDetailImpl;

import jakarta.transaction.Transactional;

@Service
public class LoginServiceImpl implements LoginService  {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public Login findUser(String email, String password) {

		if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {

			Login loginFound = loginRepository.findByEmailAndPassword(email, password);

			if (loginFound != null) {
				return loginFound; // retourne le user si trouver
				
			} else {
				return null;
			}

		} else {
			return null;
		}

	}

	@Override
	public Login save(Login login) {

		return loginRepository.save(login);
	}

	

}
