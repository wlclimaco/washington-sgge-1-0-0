package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.PersonName;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class PersonNameListValidator.
 */
public class PersonNameListValidator extends ChangeControlValidator implements IValidator
{

	/** The Constant NAME_LIST_KEY. */
	private static final String NAME_LIST_KEY = "NameList";

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(ValidationContext validationContext)
	{
		List<PersonName> personNameList = (List<PersonName>)validationContext.getObjectToBeValidated(NAME_LIST_KEY);

		if (ValidationUtil.isNullOrEmpty(personNameList))
		{
			return;
		}

		for (PersonName personName : personNameList)
		{
			if (!ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
			{
				validateChangeControlFields(validationContext.getMessages(), personName, validationContext);
			}
		}

		validationContext.getObjectsToBeValidated().put(NAME_LIST_KEY, personNameList);
	}

}
