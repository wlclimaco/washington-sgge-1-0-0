package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferStatusMaintenanceRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * MoneyTransferBatchMaintenanceValidator is the class that handles all validation for
 * {@link MoneyTransferBatchMaintenanceRequest}.
 */
public class MoneyTransferStatusMaintenanceRequestValidator extends ChangeControlValidator implements IValidator
{
	/** The error code set when the {@link MoneyTransferStatusMaintenanceRequest} object is null. */
	public static final String MONEY_TRANSFER_STATUS_REQUEST_REQUIRED =
			"sendsolv.base.moneytransferstatusrequestvalidator.moneytransferstatusrequest.required";

	/** The Constant MONEY_TRANSFER_STATUS_LIST_REQUIRED. */
	public static final String MONEY_TRANSFER_STATUS_LIST_REQUIRED =
			"sendsolv.base.moneytransferstatusrequestvalidator.batchstatuslist.required";

	/** The Constant MONEY_TRANSFER_BATCH_ID_REQUIRED. */
	public static final String MONEY_TRANSFER_ID_REQUIRED =
			"sendsolv.base.moneytransferstatusrequestvalidator.id.required";

	/** The Constant MONEY_TRANSFER_BATCH_STATUS_REQUIRED. */
	public static final String MONEY_TRANSFER_STATUS_STATUS_REQUIRED =
			"sendsolv.base.moneytransferstatusrequestvalidator.status.required";

	/**
	 * Perform validation.
	 *
	 * @param validationContext The validation context.
	 * @param moneyTransfer The {@link MoneyTransferStatus} to validate.
	 */
	protected void performValidation(ValidationContext validationContext,
			MoneyTransferStatusMaintenanceRequest moneyTransferStatusRequest)
	{
		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				break;
			case INSERT:
				validateList(validationContext, moneyTransferStatusRequest);
				break;
			case UPDATE:
				break;
			default:
				break;
		}

		if (ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
		{
			validationContext.setStopProcessing(true);
			return;
		}
	}

	@Override
	public void validate(ValidationContext validationContext)
	{
		MoneyTransferStatusMaintenanceRequest moneyTransferStatusMaintenanceRequest =
				(MoneyTransferStatusMaintenanceRequest)validationContext
						.getObjectToBeValidated(MoneyTransferStatusMaintenanceRequest.class
								.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(moneyTransferStatusMaintenanceRequest))
		{
			validationContext.getMessages().add(
					new MessageInfo(MONEY_TRANSFER_STATUS_REQUEST_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			validationContext.setStopProcessing(true);
			return;
		}

		// Validate the specific items for a money transfer batch status.
		performValidation(validationContext, moneyTransferStatusMaintenanceRequest);

	}

	/**
	 * Validate money transfer status list - the list should have at least one item.
	 *
	 * @param messages the messages
	 * @param moneyTransferStatusMaintenanceRequest the moneyTransferStatusMaintenanceRequest
	 */
	protected void validateList(ValidationContext context,
			MoneyTransferStatusMaintenanceRequest moneyTransferStatusMaintenanceRequest)
	{
		if (ValidationUtil.isNull(moneyTransferStatusMaintenanceRequest.getMoneyTransferStatusList()))
		{
			context.getMessages().add(new MessageInfo(MONEY_TRANSFER_STATUS_LIST_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}
		else
		{
			// validate the list information (item per item)
			for (MoneyTransferStatus moneyTransferStatus : moneyTransferStatusMaintenanceRequest
					.getMoneyTransferStatusList())
			{
				validateChangeControlFields(context.getMessages(), moneyTransferStatus, context);
				validateId(context.getMessages(), moneyTransferStatus);
				validateStatus(context.getMessages(), moneyTransferStatus);
			}
		}
	}

	/**
	 * Validate money transfer id.
	 *
	 * @param messages the messages
	 * @param moneyTransferStatus the moneyTransferStatus
	 */
	protected void validateId(List<MessageInfo> messages, MoneyTransferStatus moneyTransferStatus)
	{
		ValidationUtil.isNullOrZero(moneyTransferStatus.getMoneyTransferId(),
				MONEY_TRANSFER_ID_REQUIRED, messages);
	}

	/**
	 * Validate money transfer status.
	 *
	 * @param messages the messages
	 * @param moneyTransferStatus the moneyTransferStatus
	 */
	protected void validateStatus(List<MessageInfo> messages, MoneyTransferStatus moneyTransferStatus)
	{
		ValidationUtil.isNullOrZero(moneyTransferStatus.getStatusValue(),
				MONEY_TRANSFER_STATUS_STATUS_REQUIRED, messages);
	}
}
