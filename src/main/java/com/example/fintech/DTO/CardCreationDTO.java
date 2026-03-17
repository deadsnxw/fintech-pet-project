package com.example.fintech.DTO;

import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CardCreationDTO {
	private UUID userId;
}