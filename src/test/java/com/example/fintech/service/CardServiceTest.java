package com.example.fintech.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.UUID;
import java.time.LocalDate;
import java.time.Month;
import java.math.BigDecimal;
import java.util.Optional;

import com.example.fintech.model.Card;
import com.example.fintech.DTO.CardDTO;
import com.example.fintech.DTO.CardCreationDTO;
import com.example.fintech.repository.CardRepository;
import com.example.fintech.repository.UserRepository;
import com.example.fintech.model.User;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {
	@Mock
	private CardRepository cardRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private CardService cardService;

	@Test
	public void shouldCreateCard() {
		UUID uuid = UUID.randomUUID();
		CardCreationDTO dto = new CardCreationDTO(uuid);
		User user = User.builder().id(uuid).build();

		Card card = Card.builder()
			.number("1234567891113156")
			.expirationDate(LocalDate.of(2030, Month.AUGUST, 16))
			.balance(BigDecimal.valueOf(2000))
			.user(user)
			.build();

		when(userRepository.findById(any())).thenReturn(Optional.of(user));
		when(cardRepository.save(any())).thenReturn(card);

		CardDTO result = cardService.createCard(dto);

		assertEquals("1234567891113156", result.getNumber());
	}
}