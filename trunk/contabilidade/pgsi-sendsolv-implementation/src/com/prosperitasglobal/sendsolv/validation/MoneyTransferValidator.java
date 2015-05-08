package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * MoneyTransferValidator is the class that handles all validation for {@link MoneyTransfer}.
 */
public class MoneyTransferValidator extends ChangeControlValidator implements IValidator
{

	/** The error code set when the {@link MoneyTransfer} object is null. */
	public static final String MONEY_TRANSFER_REQUIRED =
			"sendsolv.base.moneytransfervalidator.moneytransfer.required";

	/** The Constant MONEY_TRANSFER_ID_REQUIRED. */
	public static final String MONEY_TRANSFER_ID_REQUIRED =
			"sendsolv.base.moneytransfer.id.required";

	/**
	 * Perform validation.
	 *
	 * @param validationContext The validation context.
	 * @param moneyTransfer The {@link MoneyTransfer} to validate.
	 */
	protected void performValidation(ValidationContext validationContext, MoneyTransfer moneyTransfer)
	{
		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				break;
			case INSERT:
				break;
			case UPDATE:
				validateId(validationContext.getMessages(), moneyTransfer);
				break;
			default:
				break;
		}

		validateChangeControlFields(validationContext.getMessages(), moneyTransfer, validationContext);

		if (ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
		{
			validationContext.setStopProcessing(true);
			return;
		}
	}

	/**
	 * Validate the {@link MoneyTransfer} object.
	 *
	 * @param validationContext The validation context.
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		MoneyTransfer moneyTransfer =
				(MoneyTransfer)validationContext.getObjectToBeValidated(MoneyTransfer.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(moneyTransfer))
		{
			validationContext.getMessages().add(
					new MessageInfo(MONEY_TRANSFER_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			validationContext.setStopProcessing(true);
			return;
		}

		// Validate the specific items for a money transfer.
		performValidation(validationContext, moneyTransfer);
	}

	/**
	 * Validate money transfer id.
	 *
	 * @param messages the messages
	 * @param moneyTransfer the moneyTransfer
	 */
	protected void validateId(List<MessageInfo> messages, MoneyTransfer moneyTransfer)
	{
		ValidationUtil.isNullOrZero(moneyTransfer.getId(),
				MONEY_TRANSFER_ID_REQUIRED, messages);
	}

}
