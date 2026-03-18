package com.example.fintech.DTO;

import lombok.*;
import java.util.UUID;
import jakarta.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CardCreationDTO {
	@NotNull(message = "User's ID is must have for card creation")
	private UUID userId;
}