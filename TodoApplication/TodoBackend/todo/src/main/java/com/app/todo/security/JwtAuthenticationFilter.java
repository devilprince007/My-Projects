package com.app.todo.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//will execute before the spring security filter to authenticate the jwt tokens
// Validate the JWT Token and Provides user details to spring Security for Authentication
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private JwtTokenProvider jwtTokenProvider;
	
	private UserDetailsService detailsService;
	
	
	
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService detailsService) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
		this.detailsService = detailsService;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain)throws ServletException, IOException {
		//Get JWT token from http request
		String token = getTokenFromRequest(request);
		
		//Validate Token
		if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
			//get Username from token
			String username = jwtTokenProvider.getUsername(token);
			
			//we load the user associated with this token
			UserDetails userDetails = detailsService.loadUserByUsername(username);
			
			//creating instance of username password authentication token object
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
																				username, 
																				null, 
																				userDetails.getAuthorities()
																	);
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			// then passing this token object to security holder
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		
		filterChain.doFilter(request, response);
		
		
	}
	
	private String getTokenFromRequest(HttpServletRequest httpServletRequest) {
		String bearerToken = httpServletRequest.getHeader("Authorization");
		
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
	
}
