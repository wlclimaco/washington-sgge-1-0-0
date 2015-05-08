package com.prosperitasglobal.sendsolv.validation;

import java.util.Map;

import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MemberPagedInquiryRequestValidator. Validates the
 * {@link com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest} for a Member fetch.
 */
public class MemberPagedInquiryRequestValidator extends PagedInquiryRequestValidator
{

	/** The Constant MEMBER_INQUIRY_REQUEST_REQUIRED. */
	private static final String MEMBER_INQUIRY_REQUEST_REQUIRED =
			"sendsolv.base.memberinquiryrequestvalidator.request.required";

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
		MemberInquiryRequest memberInquiryRequest =
				(MemberInquiryRequest)context.getObjectToBeValidated(MemberInquiryRequest.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(memberInquiryRequest))
		{
			context.getMessages().add(
					new MessageInfo(MEMBER_INQUIRY_REQUEST_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			context.setStopProcessing(true);
			return;
		}

		super.performValidation(context, memberInquiryRequest);
	}
}
