package com.example.fintech.DTO;

import lombok.*;
import java.util.UUID;
import java.time.LocalDate;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CardDTO {
	private UUID id;
	private String number;
	private LocalDate expirationDate;
	private BigDecimal balance;
	private UUID userId;
}