package com.example.fintech.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.example.fintech.validation.ValidCardNumber;

public class CardNumberValidator implements ConstraintValidator<ValidCardNumber, String> {

	@Override
	public boolean isValid(String cardNumber, ConstraintValidatorContext context) {
		if(cardNumber == null || cardNumber.isBlank()) {
			return true;
		}

		cardNumber = cardNumber.replaceAll("\\D", "");

		int sum = 0;
		boolean isSecond = false;
			
		for (int i = cardNumber.length() - 1; i >= 0; i--) {
			int digit = cardNumber.charAt(i) - '0';

			if (isSecond) {
				digit *= 2;

				if (digit > 9) digit -= 9;

				sum += digit; 
		    } else {
		    	sum += digit;
		    }

		    isSecond = !isSecond;
	    }
	    return sum % 10 == 0;
	}
}