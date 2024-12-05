package com.looser.cards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.looser.cards.constants.CardsConstants;
import com.looser.cards.dto.CardDto;
import com.looser.cards.dto.ResponseDto;
import com.looser.cards.service.ICardService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Validated
public class CardsController {
	private ICardService service;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Invalid Mobile Number. Must be of 10 digits") String mobileNumber){
		
		service.createCard(mobileNumber);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<CardDto> fetchCardDetails(@RequestParam @Pattern(regexp = "(^$|[0-9] {10})", message = "Invalid Mobile Number. must be of 10 digits") String mobileNumber){
		CardDto dto = service.fetchCard(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardDto cardDto){
		boolean isUpdated = service.updateCard(cardDto);
		if(isUpdated) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(CardsConstants.MESSAGE_200, CardsConstants.MESSAGE_200));
		} else {
			return ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
		}
	}
	
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam
                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                                String mobileNumber) {
        boolean isDeleted = service.deleteCard(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }
    }
}
