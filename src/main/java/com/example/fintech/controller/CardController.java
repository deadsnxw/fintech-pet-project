package com.example.fintech.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.UUID;

import com.example.fintech.service.CardService;
import com.example.fintech.DTO.CardDTO;
import com.example.fintech.DTO.CardCreationDTO;

@RestController
@RequestMapping("/api/cards")
public class CardController {
	private final CardService cardService;

	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CardDTO createCard(@RequestBody CardCreationDTO dto) {
		return cardService.createCard(dto);
	}

	@GetMapping("/{id}")
	public CardDTO getCardById(@PathVariable UUID id) {
		return cardService.getCardById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCard(@PathVariable UUID id) {
		cardService.deleteCard(id);
	}
}