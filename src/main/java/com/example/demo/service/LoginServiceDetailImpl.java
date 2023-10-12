package com.example.demo.service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Login;
import com.example.demo.repository.LoginRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class LoginServiceDetailImpl  {
	
	@Autowired
	private LoginRepository loginRepository;
	
	private static final  String SECRET_KET = "C8DpBlihcszclyNfWvtJulZ8Akjb6hDQ";
	
	public String extractUser(String token) {
		
		return extractClaims(token, Claims::getSubject);
	}
	
	
	
	public <T> T extractClaims(String token , Function<Claims, T> cliamsResolver) {
		
		final Claims cliams = extractAllClaims(token);
		return cliamsResolver.apply(cliams);
	}
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails );
	}
	
	public String generateToken(
			Map<String,Object> extraClaims,
			UserDetails userDetails
			) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	public boolean isTokenValid(String token , UserDetails userDetails) {
		final String username = extractUser(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
	 
		return extractExpiration(token).before(new Date());
		
	}
	
	private Date extractExpiration(String token) {
		
		return extractClaims(token, Claims::getExpiration);
	}
	
	
	
	private Claims extractAllClaims(String token) {
		
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
				
	}
	
	
	
	
	private Key getSignInKey() {
		
		byte[] KeyBytes = Decoders.BASE64.decode(SECRET_KET);
		return Keys.hmacShaKeyFor(KeyBytes);
	}
	
	@Bean
	public UserDetailsService findUserByEmail()  {

			    return  username -> loginRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found")) ;
	}

}
