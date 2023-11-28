package com.app.todo.serviceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.todo.Exception.TodoApiException;
import com.app.todo.dto.JwtAuthResponse;
import com.app.todo.dto.LoginDto;
import com.app.todo.dto.RegisterDto;
import com.app.todo.entity.Role;
import com.app.todo.entity.User;
import com.app.todo.repository.RoleRepository;
import com.app.todo.repository.UserRepository;
import com.app.todo.security.JwtTokenProvider;
import com.app.todo.service.AuthService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public String register(RegisterDto dto) {
		
		// check unique username / already exist in database  
		if (userRepository.existsByUsername(dto.getUsername())) {
			throw new TodoApiException(HttpStatus.BAD_REQUEST, "Username already exist.");
		}
		//for already existing email id
		if(userRepository.existsByEmail(dto.getEmail())) {
			throw new TodoApiException(HttpStatus.BAD_REQUEST, "Email already exist.");
		}
		
		User user = new User();
		user.setName(dto.getName());
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER");
		roles.add(userRole);
		
		user.setRoles(roles);
		
		userRepository.save(user);
		return "User registerd successfully.";
	}

	@Override
	public JwtAuthResponse login(LoginDto loginDto) {
		Authentication authentication =  authenticationManager.authenticate(
											new UsernamePasswordAuthenticationToken(
													loginDto.getUsernameOrEmail(), 
													loginDto.getPassword()
												)
											);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		
		Optional<User> userOptional = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(),loginDto.getUsernameOrEmail());
		
		String role = null;
		
		if(userOptional.isPresent()) {
			User loggedInUser = userOptional.get(); 
			Optional<Role> optionalRole = loggedInUser.getRoles().stream().findFirst();
			if(optionalRole.isPresent()) {
				Role userRole = optionalRole.get();
				role= userRole.getName();
			}
		}
		
		JwtAuthResponse authResponse = new JwtAuthResponse();
		
		authResponse.setRole(role);
		authResponse.setAccessToken(token);
		return authResponse;
	}
	
}
