package com.example.fintech.DTO;

import lombok.*;
import jakarta.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserCreationDTO {
	@NotBlank(message = "This field can't be empty")
	@Size(min = 2, max = 16, message = "The first name must be between 2 and 16 elements")
	@Pattern(regexp = ".*\\S.*", message = "The first name can't contain only spaces")
	private String firstName;

	@NotBlank(message = "This field can't be empty")
	@Size(min = 2, max = 16, message = "The last name must be between 2 and 16 elements")
	@Pattern(regexp = ".*\\S.*", message = "The last name can't contain only spaces")
	private String lastName;

	@NotBlank(message = "This field can't be empty")
	@Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Wrong phone number format")
	private String phoneNumber;

	@NotBlank(message = "This field can't be empty")
	@Size(min = 8, max = 64, message = "The password must be between 8 and 64 elements")
	private String password;
}