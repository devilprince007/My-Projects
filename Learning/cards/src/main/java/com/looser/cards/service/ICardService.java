package com.looser.cards.service;

import com.looser.cards.dto.CardDto;

public interface ICardService {
	void createCard(String mobileNumber);
	CardDto fetchCard(String mobileNumber);
	boolean updateCard(CardDto cardDto);
	boolean deleteCard(String mobileNumber);
}
