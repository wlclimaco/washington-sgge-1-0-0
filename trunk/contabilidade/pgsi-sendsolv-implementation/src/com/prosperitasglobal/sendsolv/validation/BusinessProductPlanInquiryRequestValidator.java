package com.prosperitasglobal.sendsolv.validation;

import java.util.Map;

import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class BusinessProductPlanInquiryRequestValidator. Validates the
 * {@link com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest} for a
 * {@link com.prosperitasglobal.sendsolv.model.BusinessProductPlan} fetch.
 */
public class BusinessProductPlanInquiryRequestValidator extends PagedInquiryRequestValidator
{
	/** The valid sort fields for an inquiry. Will be injected by Spring. */
	private Map<String, String> inquiryValidSortFields;

	/** The message property used when the request is missing. */
	public static final String BUSINESS_PRODUCT_PLAN_INQUIRY_REQUEST_REQUIRED =
			"sendsolv.base.businessproductplaninquiryrequestvalidator.request.required";

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
		BusinessProductPlanInquiryRequest businessProductPlanInquiryRequest =
				(BusinessProductPlanInquiryRequest)context
						.getObjectToBeValidated(BusinessProductPlanInquiryRequest.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(businessProductPlanInquiryRequest))
		{
			context.getMessages().add(
					new MessageInfo(BUSINESS_PRODUCT_PLAN_INQUIRY_REQUEST_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			context.setStopProcessing(true);
			return;
		}

		super.performValidation(context, businessProductPlanInquiryRequest);
	}
}
