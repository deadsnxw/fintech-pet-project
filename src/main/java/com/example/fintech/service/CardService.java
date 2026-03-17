package com.example.fintech.service;

import com.example.fintech.model.User;
import com.example.fintech.model.Card;
import com.example.fintech.DTO.CardDTO;
import com.example.fintech.DTO.CardCreationDTO;
import com.example.fintech.repository.CardRepository;
import com.example.fintech.repository.UserRepository;
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

	public CardService(CardRepository cardRepository, UserRepository userRepository) {
	    this.cardRepository = cardRepository;
	    this.userRepository = userRepository;
	}

	public CardDTO createCard(CardCreationDTO dto) {
		User user = userRepository.findById(dto.getUserId()).orElseThrow();

		Random random = new Random();
		StringBuilder number = new StringBuilder();
		for (int i = 0; i < 16; i++) {
		    number.append(random.nextInt(10));
		}

		LocalDate expirationDate = LocalDate.now().plusYears(4);

		BigDecimal balance = BigDecimal.ZERO;

	    Card card = Card.builder()
	        .user(user)
	        .number(number.toString())
	        .expirationDate(expirationDate)
	        .balance(balance)
	        .build();

	  	Card saved = cardRepository.save(card);

	    return CardDTO.builder()
	      .id(saved.getId())
	      .number(saved.getNumber())
	      .expirationDate(saved.getExpirationDate())
	      .balance(saved.getBalance())
	      .userId(saved.getUser().getId())
	      .build();
	}

	public CardDTO getCardById(UUID id) {
		Card card = cardRepository.findById(id).orElseThrow();

	    return CardDTO.builder()
	      .id(card.getId())
	      .number(card.getNumber())
	      .expirationDate(card.getExpirationDate())
	      .balance(card.getBalance())
	      .userId(card.getUser().getId())
	      .build();
	}

	public List<CardDTO> getAllUserCards(UUID id) {
		User user = userRepository.findById(id).orElseThrow();
		List<Card> cards = cardRepository.findAllByUserId(user.getId());

		return cards.stream()
        .map(card -> CardDTO.builder()
          .id(card.getId())
          .number(card.getNumber())
          .expirationDate(card.getExpirationDate())
          .balance(card.getBalance())
          .userId(card.getUser().getId())
          .build())
        .toList();
	}

	public void deleteCard(UUID id) {
    	cardRepository.deleteById(id);
  	}
}