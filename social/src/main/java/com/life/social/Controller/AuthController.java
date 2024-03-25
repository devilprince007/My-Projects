package com.life.social.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.life.social.Model.LoginRequest;
import com.life.social.Model.User;
import com.life.social.config.JwtProvider;
import com.life.social.response.AuthResponse;
import com.life.social.service.UserService;
import com.life.social.service.impl.CustomUserDetailsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	
	private UserService service;
	private PasswordEncoder encoder;
	private CustomUserDetailsService customUserDetailsService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> saveUser(@RequestBody User user) throws Exception {
		user.setPassword(encoder.encode(user.getPassword()));
		User data = service.registerUser(user);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse resp = AuthResponse.builder().token(token).message("Regitered successfully!!!").build();
		
		return ResponseEntity.ok(resp);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest login){
		
		Authentication authentication = authenticate(login.getEmail(), login.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse resp = AuthResponse.builder().token(token).message("Login successfull!!!").build();
		
		return new ResponseEntity<AuthResponse>(resp,HttpStatus.OK);
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
		if(userDetails == null)
			throw new BadCredentialsException("Invalid username!!!");
		if (!encoder.matches(password, userDetails.getPassword()))
			throw new BadCredentialsException("Invalid password!!!");
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	
}
