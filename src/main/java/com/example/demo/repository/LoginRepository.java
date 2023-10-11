package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dtos.LoginDTO;
import com.example.demo.entities.Login;


public interface LoginRepository extends JpaRepository<Login, Integer>{
	
	Login findByEmailAndPassword(String email, String password);

}
