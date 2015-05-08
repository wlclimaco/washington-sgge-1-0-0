package com.prosperitasglobal.sendsolv.callingcard.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.validation.ChangeControlValidator;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class DocumentValidator.
 *
 * @author aporto
 * @version 1.0
 * @created 22-Aug-2014 10:00:00 AM
 */
public class CallingCardValidator extends ChangeControlValidator implements IValidator
{
	private static final String PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_PERSON_ID_REQUIRED =
			"sendsolv.base.callingcardvalidator.person.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_AMOUNT_REQUIRED =
			"sendsolv.base.callingcardvalidator.amount.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_OBJECT_REQUIRED =
			"sendsolv.base.callingcardvalidator.object.required";

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		CallingCardInfo callingCardInfo =
				(CallingCardInfo)validationContext.getObjectToBeValidated(CallingCardInfo.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(callingCardInfo))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_OBJECT_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		/*
		 * Refill - UPDATE
		 * Assign - INSERT
		 * Balance - FETCH BY ID
		 * Fetch More - FETCH
		 * Get Available - FETCH
		 */
		switch (validationContext.getValidationContextIndicator())
		{
			case INSERT: // Assign
				validateId(validationContext.getMessages(), callingCardInfo);
				break;
			case UPDATE: // Refill
				validateId(validationContext.getMessages(), callingCardInfo);
				validateAmount(validationContext.getMessages(), callingCardInfo);
				break;
			case FETCH: // Fetch More and Get Available
				break;
			case FETCH_BY_ID: // Balance
				validateId(validationContext.getMessages(), callingCardInfo);
				break;
			default:
				validateId(validationContext.getMessages(), callingCardInfo);
				validateAmount(validationContext.getMessages(), callingCardInfo);
				break;
		}

		if (!ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
		{
			validateChangeControlFields(validationContext.getMessages(), callingCardInfo, validationContext);
		}
	}

	/**
	 * Validate id.
	 *
	 * @param list the list
	 * @param document the document
	 */
	private void validateId(List<MessageInfo> list, CallingCardInfo callingCardInfo)
	{
		if (ValidationUtil.isNullOrZero(callingCardInfo.getPersonId()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_PERSON_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate all.
	 *
	 * @param list the list
	 * @param document the document
	 */
	private void validateAmount(List<MessageInfo> list, CallingCardInfo callingCardInfo)
	{
		if (ValidationUtil.isNullOrZero(callingCardInfo.getAmount()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_AMOUNT_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}
}
