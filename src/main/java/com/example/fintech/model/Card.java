package com.example.fintech.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.UUID;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "cards")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@NotNull
	@Column
	private String number;

	@NotNull
	@Column
	private LocalDate expirationDate;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}