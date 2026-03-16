package com.example.fintech.DTO;

import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserUpdateDTO {
	private UUID id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
}