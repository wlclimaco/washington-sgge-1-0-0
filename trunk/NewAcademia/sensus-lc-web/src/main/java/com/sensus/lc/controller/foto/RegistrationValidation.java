package com.sensus.lc.controller.foto;

import javax.servlet.Registration;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component("registrationValidator")
public class RegistrationValidation
{
	public boolean supports(Class<?> klass)
	{
		return Registration.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
				"NotEmpty.registration.userName",
				"User Name must not be Empty.");

	}
}
