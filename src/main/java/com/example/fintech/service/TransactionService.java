package com.example.fintech.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.fintech.model.Card;
import com.example.fintech.DTO.TransferRequestDTO;
import com.example.fintech.exception.InsufficientFundsException;
import com.example.fintech.exception.ResourceNotFoundException;
import com.example.fintech.exception.SameCardTransferException;
import com.example.fintech.DTO.DepositRequestDTO;
import com.example.fintech.repository.CardRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.math.BigDecimal;

@Service
public class TransactionService {
	private final CardRepository cardRepository;

	public TransactionService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	} 

	@Transactional
	public void transfer(TransferRequestDTO request) {
		UUID senderId = request.getFromCardId();
		Card senderCard = cardRepository.findById(senderId)
			.orElseThrow(() -> new ResourceNotFoundException("Sender card"));

		String receiverPan = request.getToCardNumber();
		Card receiverCard = cardRepository.findByNumber(receiverPan)
			.orElseThrow(() -> new ResourceNotFoundException("Receiver card"));

		if (senderCard.getId().equals(receiverCard.getId())) {
			throw new SameCardTransferException();
		}

		BigDecimal amountToTransfer = request.getAmount();

		if(senderCard.getBalance().compareTo(amountToTransfer) < 0) {
			throw new InsufficientFundsException();
		}

		senderCard.setBalance(senderCard.getBalance().subtract(amountToTransfer));
		receiverCard.setBalance(receiverCard.getBalance().add(amountToTransfer));

		cardRepository.save(senderCard);
		cardRepository.save(receiverCard);  
	}

	@Transactional
	public void deposit(DepositRequestDTO request) {
		String receiverPan = request.getToCardNumber();
		Card receiverCard = cardRepository.findByNumber(receiverPan)
			.orElseThrow(() -> new ResourceNotFoundException("Receiver card"));

		BigDecimal amountToDeposit = request.getAmount();
		BigDecimal receiverBalance = receiverCard.getBalance();


		receiverBalance = receiverBalance.add(amountToDeposit);

		receiverCard.setBalance(receiverBalance);

		cardRepository.save(receiverCard);
	}
}