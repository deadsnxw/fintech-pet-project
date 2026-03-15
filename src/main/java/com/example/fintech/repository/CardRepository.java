package com.example.fintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

import com.example.fintech.model.Card;

public interface CardRepository extends JpaRepository<Card, UUID> {}