package com.looser.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CardAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CardAlreadyExistsException(String message) {
		super(message);
	}
}
