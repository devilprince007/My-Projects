package com.app.todo.service;

import com.app.todo.dto.JwtAuthResponse;
import com.app.todo.dto.LoginDto;
import com.app.todo.dto.RegisterDto;

public interface AuthService {
	String register(RegisterDto dto);
	JwtAuthResponse login(LoginDto loginDto);
}
