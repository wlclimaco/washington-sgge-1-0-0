package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.request.MoneyTransferAutoApprovalRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

public class MoneyTransferAutoApprovalRequestValidator implements IValidator
{

	/** The Constant MONEY_TRANSFER_AUTO_APPROVAL_REQUEST_REQUIRED. */
	public static final String MONEY_TRANSFER_AUTO_APPROVAL_REQUEST_REQUIRED =
			"sendsolv.base.moneytransferautoapprovalrequestvalidator.request.required";

	/** The Constant MONEY_TRANSFER_AUTO_APPROVAL_MONEY_TRANSFER_BATCH_ID_REQUIRED. */
	public static final String MONEY_TRANSFER_AUTO_APPROVAL_MONEY_TRANSFER_BATCH_ID_REQUIRED =
			"sendsolv.base.moneytransferautoapprovalrequestvalidator.money.transfer.batch.required";

	/** The Constant MONEY_TRANSFER_AUTO_APPROVAL_IS_NET_PAYER_GREATER_THAN_TRANSFER_REQUIRED. */
	public static final String MONEY_TRANSFER_AUTO_APPROVAL_IS_NET_PAYER_GREATER_THAN_TRANSFER_REQUIRED =
			"sendsolv.base.moneytransferautoapprovalrequestvalidator.is.net.payer.greater.than.transfer.required";

	/** The Constant MONEY_TRANSFER_AUTO_APPROVAL_IS_PREVIOUS_TRANSACTION_APPROVED_REQUIRED. */
	public static final String MONEY_TRANSFER_AUTO_APPROVAL_IS_PREVIOUS_TRANSACTION_APPROVED_REQUIRED =
			"sendsolv.base.moneytransferautoapprovalrequestvalidator.is.previous.transaction.approved.required";

	/** The Constant MONEY_TRANSFER_AUTO_APPROVAL_IS_SAME_AMOUNT_PREVIOUS_TRANSACTION_REQUIRED. */
	public static final String MONEY_TRANSFER_AUTO_APPROVAL_IS_SAME_AMOUNT_PREVIOUS_TRANSACTION_REQUIRED =
			"sendsolv.base.moneytransferautoapprovalrequestvalidator.is.same.amount.previous.transaction.required";

	@Override
	public void validate(ValidationContext validationContext)
	{
		MoneyTransferAutoApprovalRequest autoApprovalRequest =
				(MoneyTransferAutoApprovalRequest)validationContext
						.getObjectToBeValidated(MoneyTransferAutoApprovalRequest.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(autoApprovalRequest))
		{
			validationContext.getMessages().add(
					new MessageInfo(MONEY_TRANSFER_AUTO_APPROVAL_REQUEST_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			validationContext.setStopProcessing(true);
			return;
		}

		performValidation(validationContext, autoApprovalRequest);
	}

	/**
	 * Perform validation.
	 *
	 * @param validationContext The validation context.
	 * @param autoApprovalRequest the auto approval request
	 */
	protected void performValidation(ValidationContext validationContext,
			MoneyTransferAutoApprovalRequest autoApprovalRequest)
	{
		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				break;
			case INSERT:
				break;
			case UPDATE:
				break;
			case FETCH:
				validateId(validationContext.getMessages(), autoApprovalRequest);
				validateAutoApprovalOptions(validationContext.getMessages(), autoApprovalRequest);
				break;
			default:
				break;
		}

	}

	/**
	 * Validate money transfer batch id.
	 *
	 * @param messages the messages
	 * @param autoApprovalRequest the auto approval request
	 */
	protected void validateId(List<MessageInfo> messages, MoneyTransferAutoApprovalRequest autoApprovalRequest)
	{
		ValidationUtil.isNullOrZero(autoApprovalRequest.getMoneyTransferBatchId(),
				MONEY_TRANSFER_AUTO_APPROVAL_MONEY_TRANSFER_BATCH_ID_REQUIRED, messages);
	}

	/**
	 * Validate auto approval options.
	 *
	 * @param messages the messages
	 * @param autoApprovalRequest the auto approval request
	 */
	protected void validateAutoApprovalOptions(List<MessageInfo> messages,
			MoneyTransferAutoApprovalRequest autoApprovalRequest)
	{
		ValidationUtil.isNull(autoApprovalRequest.getIsNetPayGreaterTransValue(),
				MONEY_TRANSFER_AUTO_APPROVAL_IS_NET_PAYER_GREATER_THAN_TRANSFER_REQUIRED, messages);

		ValidationUtil.isNull(autoApprovalRequest.getIsPreviousTransactionApproved(),
				MONEY_TRANSFER_AUTO_APPROVAL_IS_PREVIOUS_TRANSACTION_APPROVED_REQUIRED, messages);

		ValidationUtil.isNull(autoApprovalRequest.getIsSameAmountPreviousTransaction(),
				MONEY_TRANSFER_AUTO_APPROVAL_IS_SAME_AMOUNT_PREVIOUS_TRANSACTION_REQUIRED, messages);

	}
}
