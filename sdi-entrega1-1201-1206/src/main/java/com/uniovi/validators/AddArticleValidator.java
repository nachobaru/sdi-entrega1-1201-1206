package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Article;

@Component
public class AddArticleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Article.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Error.empty");		
	}
}
