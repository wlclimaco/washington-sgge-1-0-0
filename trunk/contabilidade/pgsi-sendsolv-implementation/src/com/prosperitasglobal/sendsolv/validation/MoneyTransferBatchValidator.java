package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * MoneyTransferBatchValidator is the class that handles all validation for {@link MoneyTransferBatch}.
 */
public class MoneyTransferBatchValidator extends ChangeControlValidator implements IValidator
{

	/** The error code set when the {@link MoneyTransferBatch} object is null. */
	public static final String MONEY_TRANSFER_BATCH_REQUIRED =
			"sendsolv.base.moneytransferbatchvalidator.moneytransferbatch.required";

	/** The Constant MONEY_TRANSFER_BATCH_ID_REQUIRED. */
	public static final String MONEY_TRANSFER_BATCH_ID_REQUIRED =
			"sendsolv.base.moneytransferbatch.id.required";

	/** The location dac. */
	private ILocationDAC locationDAC;

	/** The organization dac. */
	private IOrganizationDAC organizationDAC;

	/**
	 * @return the locationDAC
	 */
	public ILocationDAC getLocationDAC()
	{
		return locationDAC;
	}

	/**
	 * @param locationDAC the locationDAC to set
	 */
	public void setLocationDAC(ILocationDAC locationDAC)
	{
		this.locationDAC = locationDAC;
	}

	/**
	 * @return the organizationDAC
	 */
	public IOrganizationDAC getOrganizationDAC()
	{
		return organizationDAC;
	}

	/**
	 * @param organizationDAC the organizationDAC to set
	 */
	public void setOrganizationDAC(IOrganizationDAC organizationDAC)
	{
		this.organizationDAC = organizationDAC;
	}

	/**
	 * Perform validation.
	 *
	 * @param validationContext The validation context.
	 * @param moneyTransferBatch The {@link MoneyTransferBatch} to validate.
	 */
	protected void performValidation(ValidationContext validationContext, MoneyTransferBatch moneyTransferBatch)
	{
		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				break;
			case INSERT:
				break;
			case UPDATE:
				validateId(validationContext.getMessages(), moneyTransferBatch);
				break;
			default:
				break;
		}

		validateChangeControlFields(validationContext.getMessages(), moneyTransferBatch, validationContext);

		if (ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
		{
			validationContext.setStopProcessing(true);
			return;
		}
	}

	@Override
	public void validate(ValidationContext validationContext)
	{
		MoneyTransferBatch moneyTransferBatch =
				(MoneyTransferBatch)validationContext.getObjectToBeValidated(MoneyTransferBatch.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(moneyTransferBatch))
		{
			validationContext.getMessages().add(
					new MessageInfo(MONEY_TRANSFER_BATCH_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			validationContext.setStopProcessing(true);
			return;
		}

		// Validate the specific items for a money transfer batch.
		performValidation(validationContext, moneyTransferBatch);
	}

	/**
	 * Validate money transfer batch id.
	 *
	 * @param messages the messages
	 * @param moneyTransferBatch the moneyTransferBatch
	 */
	protected void validateId(List<MessageInfo> messages, MoneyTransferBatch moneyTransferBatch)
	{
		ValidationUtil.isNullOrZero(moneyTransferBatch.getId(),
				MONEY_TRANSFER_BATCH_ID_REQUIRED, messages);
	}
}
