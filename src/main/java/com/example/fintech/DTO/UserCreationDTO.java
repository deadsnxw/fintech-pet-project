package com.example.fintech.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserCreationDTO {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String password;
}