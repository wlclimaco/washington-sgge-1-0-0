package com.prosperitasglobal.sendsolv.validation;

import java.util.Map;

import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class PayerInquiryRequestValidator. Validates the
 * {@link com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest} for a
 * {@link com.prosperitasglobal.sendsolv.model.Payer} fetch.
 */
public class PayerInquiryRequestValidator extends PagedInquiryRequestValidator
{
	/** The valid sort fields for an inquiry. Will be injected by Spring. */
	private Map<String, String> inquiryValidSortFields;

	/** The message property used when the request is missing. */
	public static final String PAYER_INQUIRY_REQUEST_REQUIRED =
			"sendsolv.base.payerinquiryrequestvalidator.request.required";

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
		PayerInquiryRequest payerInquiryRequest =
				(PayerInquiryRequest)context.getObjectToBeValidated(PayerInquiryRequest.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(payerInquiryRequest))
		{
			context.getMessages().add(
					new MessageInfo(PAYER_INQUIRY_REQUEST_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			context.setStopProcessing(true);
			return;
		}

		super.performValidation(context, payerInquiryRequest);
	}
}
