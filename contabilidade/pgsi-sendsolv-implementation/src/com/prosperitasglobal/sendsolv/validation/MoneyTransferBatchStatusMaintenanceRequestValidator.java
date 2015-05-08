package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatus;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchStatusMaintenanceRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * MoneyTransferBatchStatusMaintenanceValidator is the class that handles all validation for
 * {@link MoneyTransferBatchStatusMaintenanceRequest}.
 */
public class MoneyTransferBatchStatusMaintenanceRequestValidator extends ChangeControlValidator implements IValidator
{

	/** The error code set when the {@link MoneyTransferBatchStatusMaintenanceRequest} object is null. */
	public static final String MONEY_TRANSFER_BATCH_STATUS_REQUEST_REQUIRED =
			"sendsolv.base.moneytransferbatchstatusrequestvalidator.moneytransferbatchstatusrequest.required";

	/** The Constant MONEY_TRANSFER_BATCH_ID_REQUIRED. */
	public static final String MONEY_TRANSFER_BATCH_STATUS_LIST_REQUIRED =
			"sendsolv.base.moneytransferbatchstatusrequestvalidator.batchstatuslist.required";

	/** The Constant MONEY_TRANSFER_BATCH_ID_REQUIRED. */
	public static final String MONEY_TRANSFER_BATCH_ID_REQUIRED =
			"sendsolv.base.moneytransferbatchstatusrequestvalidator.batch.id.required";

	/** The Constant MONEY_TRANSFER_BATCH_STATUS_REQUIRED. */
	public static final String MONEY_TRANSFER_BATCH_STATUS_STATUS_REQUIRED =
			"sendsolv.base.moneytransferbatchstatusrequestvalidator.status.required";

	/**
	 * Perform validation.
	 *
	 * @param validationContext The validation context.
	 * @param moneyTransfer The {@link MoneyTransferBatchStatus} to validate.
	 */
	protected void performValidation(ValidationContext validationContext,
			MoneyTransferBatchStatusMaintenanceRequest moneyTransferBatchStatusRequest)
	{
		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				break;
			case INSERT:
				validateList(validationContext, moneyTransferBatchStatusRequest);
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
		MoneyTransferBatchStatusMaintenanceRequest moneyTransferBatchStatusMaintenanceRequest =
				(MoneyTransferBatchStatusMaintenanceRequest)validationContext
				.getObjectToBeValidated(MoneyTransferBatchStatusMaintenanceRequest.class
						.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(moneyTransferBatchStatusMaintenanceRequest))
		{
			validationContext.getMessages().add(
					new MessageInfo(MONEY_TRANSFER_BATCH_STATUS_REQUEST_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			validationContext.setStopProcessing(true);
			return;
		}

		// Validate the specific items for a money transfer batch status.
		performValidation(validationContext, moneyTransferBatchStatusMaintenanceRequest);

	}

	/**
	 * Validate money transfer batch status list - the list should have at least one item.
	 *
	 * @param messages the messages
	 * @param moneyTransferBatchStatusMaintenanceRequest the moneyTransferBatchStatusMaintenanceRequest
	 */
	protected void validateList(ValidationContext context,
			MoneyTransferBatchStatusMaintenanceRequest moneyTransferBatchStatusMaintenanceRequest)
	{
		if (ValidationUtil.isNull(moneyTransferBatchStatusMaintenanceRequest.getMoneyTransferBatchStatusList()))
		{
			context.getMessages().add(new MessageInfo(MONEY_TRANSFER_BATCH_STATUS_LIST_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}
		else
		{
			// validate the list information (item per item)
			for (MoneyTransferBatchStatus moneyTransferBatchStatus : moneyTransferBatchStatusMaintenanceRequest
					.getMoneyTransferBatchStatusList())
			{
				validateChangeControlFields(context.getMessages(), moneyTransferBatchStatus, context);
				validateId(context.getMessages(), moneyTransferBatchStatus);
				validateStatus(context.getMessages(), moneyTransferBatchStatus);
			}
		}
	}

	/**
	 * Validate money transfer batch id.
	 *
	 * @param messages the messages
	 * @param moneyTransferBatchStatus the moneyTransferBatchStatus
	 */
	protected void validateId(List<MessageInfo> messages, MoneyTransferBatchStatus moneyTransferBatchStatus)
	{
		ValidationUtil.isNullOrZero(moneyTransferBatchStatus.getMoneyTransferBatchId(),
				MONEY_TRANSFER_BATCH_ID_REQUIRED, messages);
	}

	/**
	 * Validate money transfer batch status.
	 *
	 * @param messages the messages
	 * @param moneyTransferBatchStatus the moneyTransferBatchStatus
	 */
	protected void validateStatus(List<MessageInfo> messages, MoneyTransferBatchStatus moneyTransferBatchStatus)
	{
		ValidationUtil.isNullOrZero(moneyTransferBatchStatus.getStatusValue(),
				MONEY_TRANSFER_BATCH_STATUS_STATUS_REQUIRED, messages);
	}

}
