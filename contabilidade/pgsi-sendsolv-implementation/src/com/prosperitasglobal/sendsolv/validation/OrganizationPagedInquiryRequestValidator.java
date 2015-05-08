package com.prosperitasglobal.sendsolv.validation;

import java.util.Map;

import com.qat.framework.validation.ValidationContext;

/**
 * The Class OrganizationPagedInquiryRequestValidator. Validates the
 * {@link com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest} for an Organization fetch.
 */
public class OrganizationPagedInquiryRequestValidator extends PagedInquiryRequestValidator
{
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
		super.validate(context);
	}
}
