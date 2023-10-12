package com.example.demo.security;

import java.util.Date;
import java.security.Key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Login;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JwtUtils {
	
	
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeHttpRequests().requestMatchers("").permitAll().anyRequest().authenticated().and().sessionManagement()
		.sessionCreationPolicy(null)
		.and()
		.authenticationProvider(AuthenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	
	
	
	
	
	
	
	

//	  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
//
//	  @Value("${bezkoder.app.jwtSecret}")
//	  private String jwtSecret;
//
//	  @Value("${bezkoder.app.jwtExpirationMs}")
//	  private int jwtExpirationMs;
//
//	  public String generateJwtToken(Authentication authentication) {
//
//	    Login userPrincipal = (Login) authentication.getPrincipal();
//
//	    return Jwts.builder()
//	        .setSubject((userPrincipal.getEmail()))
//	        .setIssuedAt(new Date())
//	        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//	        .signWith(key(), SignatureAlgorithm.HS256)
//	        .compact();
//	  }
//	  
//	  private Key key() {
//	    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//	  }
//
//	  public String getUserNameFromJwtToken(String token) {
//	    return Jwts.parserBuilder().setSigningKey(key()).build()
//	               .parseClaimsJws(token).getBody().getSubject();
//	  }
//
//	  public boolean validateJwtToken(String authToken) {
//	    try {
//	      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
//	      return true;
//	    } catch (MalformedJwtException e) {
//	      logger.error("Invalid JWT token: {}", e.getMessage());
//	    } catch (ExpiredJwtException e) {
//	      logger.error("JWT token is expired: {}", e.getMessage());
//	    } catch (UnsupportedJwtException e) {
//	      logger.error("JWT token is unsupported: {}", e.getMessage());
//	    } catch (IllegalArgumentException e) {
//	      logger.error("JWT claims string is empty: {}", e.getMessage());
//	    }
//
//	    return false;
//	  }
}
