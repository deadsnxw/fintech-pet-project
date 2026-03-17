package com.example.fintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.util.Optional;
import java.util.List;

import com.example.fintech.model.Card;

public interface CardRepository extends JpaRepository<Card, UUID> {
	List<Card> findAllByUserId(UUID userId);

	@Transactional
	void deleteAllByUserId(UUID userId);
}