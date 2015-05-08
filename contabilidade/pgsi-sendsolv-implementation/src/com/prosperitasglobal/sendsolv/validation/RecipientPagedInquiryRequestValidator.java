package com.prosperitasglobal.sendsolv.validation;

import java.util.Map;

import com.prosperitasglobal.sendsolv.model.request.RecipientInquiryRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class RecipientPagedInquiryRequestValidator. Validates the
 * {@link com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest} for a Recipient fetch.
 */
public class RecipientPagedInquiryRequestValidator extends PagedInquiryRequestValidator
{

	private static final String RECIPIENT_INQUIRY_REQUEST_REQUIRED =
			"sendsolv.base.recipientinquiryrequestvalidator.request.required";

	/** The valid sort fields for an inquiry. Will be injected by Spring. */
	private Map<String, String> inquiryValidSortFields;

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

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		RecipientInquiryRequest recipientInquiryRequest =
				(RecipientInquiryRequest)context.getObjectToBeValidated(RecipientInquiryRequest.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(recipientInquiryRequest))
		{
			context.getMessages().add(
					new MessageInfo(RECIPIENT_INQUIRY_REQUEST_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			context.setStopProcessing(true);
			return;
		}

		super.performValidation(context, recipientInquiryRequest);
	}
}
