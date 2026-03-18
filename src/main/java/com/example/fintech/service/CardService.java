package com.example.fintech.service;

import com.example.fintech.model.User;
import com.example.fintech.model.Card;
import com.example.fintech.DTO.CardDTO;
import com.example.fintech.DTO.CardCreationDTO;
import com.example.fintech.repository.CardRepository;
import com.example.fintech.repository.UserRepository;
import com.example.fintech.mapper.CardMapper;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;
import java.time.LocalDate;
import java.util.Random;
import java.math.BigDecimal;

@Service
public class CardService {
	private final CardRepository cardRepository;
	private final UserRepository userRepository;

	private final CardMapper cardMapper;

	public CardService(CardRepository cardRepository, UserRepository userRepository, CardMapper cardMapper) {
	    this.cardRepository = cardRepository;
	    this.userRepository = userRepository;
	    this.cardMapper = cardMapper;
	}

	public CardDTO createCard(CardCreationDTO dto) {
		User user = userRepository.findById(dto.getUserId()).orElseThrow();

		LocalDate expirationDate = LocalDate.now().plusYears(4);

		String cardNumber;

		do {
			cardNumber = generateCardNumber();
		}
		while (cardRepository.existsByNumber(cardNumber));

		BigDecimal balance = BigDecimal.ZERO;

		Card card = Card.builder()
	        .user(user)
	        .number(cardNumber)
	        .expirationDate(expirationDate)
	        .balance(balance)
	        .build();

	    Card savedCard = cardRepository.save(card);    

	    return cardMapper.toDto(savedCard);
	}

	public CardDTO getCardById(UUID id) {
		return cardRepository.findById(id)
				.map(cardMapper::toDto)
				.orElseThrow(() -> new RuntimeException("Card not found"));
	}

	public List<CardDTO> getAllUserCards(UUID userId) {
		return cardRepository.findAllByUserId(userId).stream()
				.map(cardMapper::toDto)
				.toList();
	}

	public void deleteCard(UUID id) {
    	cardRepository.deleteById(id);
  	}

  	private String generateCardNumber() {
  		String BIN = "414141";

  		Random random = new Random();
		StringBuilder uniqueNumber = new StringBuilder();
		for (int i = 0; i < 9; i++) {
		    uniqueNumber.append(random.nextInt(10));
		}

		String cardNumber = BIN + uniqueNumber + "0";
		int sum = 0;
		boolean isSecond = false;
		
		for (int i = cardNumber.length() - 1; i >= 0; i--) {
			int digit = cardNumber.charAt(i) - '0';

			if (isSecond) {
				digit *= 2;

				if (digit > 9) digit -= 9;

				sum += digit; 
		    } else {
		    	sum += digit;
		    }

		    isSecond = !isSecond;
	    }
	    int controlDigit = (10 - (sum % 10)) % 10;

	    return BIN + uniqueNumber + controlDigit;
  	}
}