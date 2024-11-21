package com.looser.cards.service;

import com.looser.cards.dto.CardDto;

public interface ICardService {
	void createCard(String mobileNumber);
	boolean fetchCard(String mobileNumber);
	CardDto updateCard(CardDto cardDto);
	boolean deleteCard(String mobileNumber);
}
