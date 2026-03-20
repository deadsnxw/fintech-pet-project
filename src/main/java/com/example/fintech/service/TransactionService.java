package com.example.fintech.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.fintech.model.Card;
import com.example.fintech.DTO.TransferRequestDTO;
import com.example.fintech.DTO.DepositRequestDTO;
import com.example.fintech.repository.CardRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class TransactionService {
	private final CardRepository cardRepository;

	public TransactionService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	} 

	@Transactional
	public void transfer(TransferRequestDTO request) {
		UUID senderId = request.getFromCardId();
		Card senderCard = cardRepository.findById(senderId).orElseThrow(() -> new NoSuchElementException("Resource not found in the database"));

		String receiverPan = request.getToCardNumber();
		Card receiverCard = cardRepository.findByNumber(receiverPan).orElseThrow(() -> new NoSuchElementException("Resource not found in the database"));

		BigDecimal amountToTransfer = request.getAmount();
		BigDecimal receiverBalance = receiverCard.getBalance();
		BigDecimal senderBalance = senderCard.getBalance();

		if(senderBalance.compareTo(amountToTransfer) < 0) {
			throw new RuntimeException("Not enough money");
		}

		senderBalance = senderBalance.subtract(amountToTransfer);
		receiverBalance = receiverBalance.add(amountToTransfer);

		senderCard.setBalance(senderBalance);
		receiverCard.setBalance(receiverBalance);

		cardRepository.save(senderCard);
		cardRepository.save(receiverCard);  
	}

	@Transactional
	public void deposit(DepositRequestDTO request) {
		String receiverPan = request.getToCardNumber();
		Card receiverCard = cardRepository.findByNumber(receiverPan).orElseThrow(() -> new NoSuchElementException("Resource not found in the database"));

		BigDecimal amountToDeposit = request.getAmount();
		BigDecimal receiverBalance = receiverCard.getBalance();


		receiverBalance = receiverBalance.add(amountToDeposit);

		receiverCard.setBalance(receiverBalance);

		cardRepository.save(receiverCard);
	}
}