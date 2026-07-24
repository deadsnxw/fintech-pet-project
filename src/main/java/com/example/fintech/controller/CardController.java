package com.example.fintech.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;
import jakarta.validation.Valid;

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
	@PreAuthorize("hasRole('ADMIN') or #dto.userId == authentication.principal.id")
	@ResponseStatus(HttpStatus.CREATED)
	public CardDTO createCard(@Valid @RequestBody CardCreationDTO dto) {
		return cardService.createCard(dto);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or @cardAuthChecker.isOwner(#id, authentication)")
	public CardDTO getCardById(@PathVariable UUID id) {
		return cardService.getCardById(id);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or @cardAuthChecker.isOwner(#id, authentication)")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCard(@PathVariable UUID id) {
		cardService.deleteCard(id);
	}
}