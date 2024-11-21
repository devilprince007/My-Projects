package com.looser.cards.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.looser.cards.constants.CardsConstants;
import com.looser.cards.dto.CardDto;
import com.looser.cards.entity.Cards;
import com.looser.cards.exception.CardAlreadyExistsException;
import com.looser.cards.exception.ResourceNotFoundException;
import com.looser.cards.repository.CardRepository;
import com.looser.cards.service.ICardService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService{
	private CardRepository repository;

	@Override
	public void createCard(String mobileNumber) {
		Optional<Cards> card = repository.findByMobileNumber(mobileNumber);
		if(card.isPresent()) {
			throw new CardAlreadyExistsException("Card already registered with given mobileNumber: "+mobileNumber);
		}
		repository.save(createNewCard(mobileNumber));
	}

	@Override
	public boolean fetchCard(String mobileNumber) {
		Cards card = repository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException(("Card", "mobileNumber", mobileNumber)));
		
		return false;
	}

	@Override
	public CardDto updateCard(CardDto cardDto) {
		
		return null;
	}

	@Override
	public boolean deleteCard(String mobileNumber) {
		
		return false;
	}
	
	private Cards createNewCard(String mobileNumber) {
		Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        
        return newCard;
	}
}
