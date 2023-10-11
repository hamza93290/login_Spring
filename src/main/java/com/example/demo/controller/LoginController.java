package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Login;
import com.example.demo.service.LoginService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping()
	ResponseEntity<Login> postClient(@RequestBody Login login ) {
		
		  try {
	            return new ResponseEntity<>(loginService.findUser(login.getEmail(), login.getPassword()), HttpStatus.OK);
	        } catch (RuntimeException e){
	            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	}
}
