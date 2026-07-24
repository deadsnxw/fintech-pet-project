package com.example.fintech.security;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.fintech.repository.CardRepository;

@Component("cardAuthChecker")
public class CardAuthorizationChecker {
    private final CardRepository cardRepository;

    public CardAuthorizationChecker(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public boolean isOwner(UUID cardId, Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        return cardRepository.findById(cardId)
                .map(card -> card.getUser().getId().equals(principal.getId()))
                .orElse(false);
    }
}