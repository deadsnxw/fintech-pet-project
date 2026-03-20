package com.example.fintech.DTO;

import com.example.fintech.validation.ValidCardNumber;

import lombok.*;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DepositRequestDTO {

	@NotBlank(message = "This field can't be empty")
	@ValidCardNumber
	private String toCardNumber;

	@DecimalMin(value = "0.01")
	private BigDecimal amount;
}