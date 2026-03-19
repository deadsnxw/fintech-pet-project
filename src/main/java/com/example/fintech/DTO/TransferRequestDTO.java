package com.example.fintech.DTO;

import com.example.fintech.validation.ValidCardNumber;

import lombok.*;
import java.util.UUID;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransferRequestDTO {

	@NotNull
	private UUID fromCardId;

	@NotBlank(message = "This field can't be empty")
	@ValidCardNumber
	private String toCardNumber;

	@DecimalMin(value = "0.01")
	private BigDecimal amount;
}