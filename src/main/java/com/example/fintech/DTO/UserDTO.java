package com.example.fintech.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {
	private String firstName;
	private String lastName;
	private String phoneNumber;
}