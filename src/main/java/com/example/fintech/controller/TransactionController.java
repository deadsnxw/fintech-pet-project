package com.example.fintech.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.UUID;
import jakarta.validation.Valid;

import com.example.fintech.service.TransactionService;
import com.example.fintech.DTO.TransferRequestDTO;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/transfer")
	public String transfer(@Valid @RequestBody TransferRequestDTO dto) {
		transactionService.transfer(dto);
		return "Transfer Successful !";
	}
}