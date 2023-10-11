//package com.example.demo.repository;
//
//import org.springframework.stereotype.Component;
//
//import com.example.demo.entities.Login;
//import com.example.demo.service.LoginService;
//
//import jakarta.annotation.PostConstruct;
//
//@Component
//public class DatabaseInt {
//	
//private LoginService loginService;
//
//public DatabaseInt(LoginService loginService) {
//	this.loginService = loginService;
//}
//
//@PostConstruct
//public void loadData() {
//	
//	Login login = new Login( "ha@.fr", "passworrrd");
//	
//	loginService.save(login);
//}
//
//
//
//}