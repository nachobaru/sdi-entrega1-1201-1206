package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UserService;
@Component
public class LoginFormValidator implements Validator{
	@Autowired
	private UserService userService;
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User u= (User) target;
//		if(userService.searchUser(u)==null) {
			//errors.reject("username","Error.empty");
		//}
	
	}
}
