package com.example.fintech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError handleNotFoundException(NoSuchElementException ex) {
		return new ApiError(404, "Resource not found in the database");
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleBadRequestException(ConstraintViolationException ex) {
		return new ApiError(400, "You missed a mandatory field");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleIllegalArgumentException(IllegalArgumentException ex) {
		return new ApiError(400, "This argument can't be placed here");
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		return new ApiError(400, "Wrong parameter type in URL");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleNotValid(MethodArgumentNotValidException ex) {
		return new ApiError(400, ex.getBindingResult().getFieldError().getDefaultMessage());
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError handleResourceNotFoundException(ResourceNotFoundException ex) {
		return new ApiError(404, ex.getMessage());
	}

	@ExceptionHandler(InsufficientFundsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleInsufficientFundsException(InsufficientFundsException ex) {
		return new ApiError(400, ex.getMessage());
	}

	@ExceptionHandler(SameCardTransferException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleSameCardTransferException(SameCardTransferException ex) {
		return new ApiError(400, ex.getMessage());
	}

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ApiError handleBadCredentialsException(BadCredentialsException ex) {
		return new ApiError(401, ex.getMessage());
	}
}