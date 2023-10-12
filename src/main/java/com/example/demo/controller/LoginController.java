package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Login;
import com.example.demo.payload.JwtResponse;
import com.example.demo.payload.LoginRequest;
import com.example.demo.security.JwtUtils;

import jakarta.validation.Valid;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
public class LoginController {
	
	
	
	
	//@Autowired
    AuthenticationManager authenticationManager;
	
	//@Autowired
	PasswordEncoder encoder;
	
    //@Autowired
	JwtUtils jwtUtils;
	
	
    
	
//public LoginController(AuthenticationManager authenticationManager, PasswordEncoder encoder, JwtUtils jwtUtils) {
//		this.authenticationManager = authenticationManager;
//		this.encoder = encoder;
//		this.jwtUtils = jwtUtils;
//	}




//	@PostMapping()
//	ResponseEntity<Login> postClient(@RequestBody Login login ) {
//		
//		  try {
//	            return new ResponseEntity<>(loginService.findUser(login.getEmail(), login.getPassword()), HttpStatus.OK);
//	        } catch (RuntimeException e){
//	            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//	        }
//	}
	
	
	  @PostMapping()
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    Login userDetails = (Login) authentication.getPrincipal();   

	    return ResponseEntity.ok(new JwtResponse(jwt, 
	                         userDetails.getId(),  
	                         userDetails.getEmail()
	                         ));
	  }
}
