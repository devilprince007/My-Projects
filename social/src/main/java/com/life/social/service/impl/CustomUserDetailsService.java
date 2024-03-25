package com.life.social.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.life.social.Model.User;
import com.life.social.Repository.UserRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{

	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByEmail(username);
		if(user == null)
				throw new UsernameNotFoundException("User not found with email "+username);
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
	
}
