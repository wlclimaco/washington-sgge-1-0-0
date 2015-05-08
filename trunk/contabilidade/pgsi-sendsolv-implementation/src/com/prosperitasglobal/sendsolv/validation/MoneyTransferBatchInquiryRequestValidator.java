package com.prosperitasglobal.sendsolv.validation;

import java.util.Map;

import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MoneyTransferBatchInquiryRequestValidator validates the :
 * {@link com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest} for a
 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferBatch}.
 */
public class MoneyTransferBatchInquiryRequestValidator extends PagedInquiryRequestValidator
{
	/** The valid sort fields for an inquiry. Will be injected by Spring. */
	private Map<String, String> inquiryValidSortFields;

	/** The message property used when the request is missing. */
	public static final String MONEY_TRANSFER_BATCH_INQUIRY_REQUEST_REQUIRED =
			"sendsolv.base.moneytransferbatchinquiryrequestvalidator.request.required";

	/**
	 * Get the valid sort fields for the inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the inquiry.
	 */
	@Override
	public Map<String, String> getInquiryValidSortFields()
	{
		return inquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the inquiry. Attribute injected by Spring.
	 *
	 * @param inquiryValidSortFields The valid sort fields for the inquiry to set.
	 */
	@Override
	public void setInquiryValidSortFields(Map<String, String> inquiryValidSortFields)
	{
		this.inquiryValidSortFields = inquiryValidSortFields;
	}

	@Override
	public void validate(ValidationContext context)
	{
		MoneyTransferBatchInquiryRequest moneyTransferBatchInquiryRequest =
				(MoneyTransferBatchInquiryRequest)context.getObjectToBeValidated(MoneyTransferBatchInquiryRequest.class
						.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(moneyTransferBatchInquiryRequest))
		{
			context.getMessages().add(
					new MessageInfo(MONEY_TRANSFER_BATCH_INQUIRY_REQUEST_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			context.setStopProcessing(true);
			return;
		}

		super.performValidation(context, moneyTransferBatchInquiryRequest);
	}

}
