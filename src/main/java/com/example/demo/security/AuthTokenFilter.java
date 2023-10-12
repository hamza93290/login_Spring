package com.example.demo.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.LoginService;
import com.example.demo.service.LoginServiceDetailImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
	
	
	  @Autowired
	  private JwtUtils jwtUtils;

	  @Autowired
	  private LoginService loginService;
	  
	  
	  private final LoginServiceDetailImpl loginServiceDetailImpl = new LoginServiceDetailImpl();
	  
	  private  UserDetailsService userDetailsService ;
	  

	  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	  @Override
	  protected void doFilterInternal( @NonNull HttpServletRequest request,
									   @NonNull HttpServletResponse response,
									   @NonNull FilterChain filterChain)
	      throws ServletException, IOException {
		  
		  final String authHeader = request.getHeader("Authorization");
		  final String jwt;
		  final String userEmail;
		  if (authHeader == null || authHeader.startsWith("Bearer")) {
			
			  filterChain.doFilter(request, response);
			  return;
		}
		  jwt = authHeader.substring(7);
		  userEmail = loginServiceDetailImpl.extractUser(jwt); // on doit extraite l'eamil de l'user avec le token 
		  if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			  
			  UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
			  if(loginServiceDetailImpl.isTokenValid(jwt, userDetails)) {
				  
				  UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				  authToken.setDetails(
						  new WebAuthenticationDetailsSource().buildDetails(request)
						  );
				  
				  SecurityContextHolder.getContext().setAuthentication(authToken);
			  }
		  }
		  
		  
		  filterChain.doFilter(request, response);
		  
		  
		  
		  
		  
		  
		  
		  
	    try {
	      String jwt = parseJwt(request);
	      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
	        String email = jwtUtils.getUserNameFromJwtToken(jwt);

	        UserDetails userDetails = loginService.findUserByEmail(email);
	        UsernamePasswordAuthenticationToken authentication =
	            new UsernamePasswordAuthenticationToken(
	                userDetails,
	                null,
	                userDetails.getAuthorities());
	        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	      }
	    } catch (Exception e) {
	      logger.error("Cannot set user authentication: {}", e);
	    }

	    filterChain.doFilter(request, response);
	  }

	  private String parseJwt(HttpServletRequest request) {
	    String headerAuth = request.getHeader("Authorization");

	    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
	      return headerAuth.substring(7);
	    }

	    return null;
	  }

	

}
