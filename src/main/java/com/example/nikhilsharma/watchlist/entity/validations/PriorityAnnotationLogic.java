package com.example.nikhilsharma.watchlist.entity.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriorityAnnotationLogic implements ConstraintValidator<Priority, String> {
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return value.trim().length() == 1 && "LHM".contains(value.trim());
	}

}


