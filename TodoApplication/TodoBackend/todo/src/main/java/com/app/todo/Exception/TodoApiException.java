package com.app.todo.Exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String message;
	
	
}
