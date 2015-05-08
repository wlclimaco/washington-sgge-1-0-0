package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.TemplateProductPlanCriteria;

/**
 * The template product plan inquiry request is used to do inquiry lookups on the data store, using the criteria
 * specified in the <code>criteria</code> property.
 */
public class TemplateProductPlanInquiryRequest extends PagedInquiryRequest
{
	/** The criteria to use in the inquiry request. */
	private TemplateProductPlanCriteria criteria;

	/**
	 * Get the criteria for the lookup.
	 *
	 * @return The criteria.
	 */
	public TemplateProductPlanCriteria getCriteria()
	{
		if (null == criteria)
		{
			criteria = new TemplateProductPlanCriteria();
		}

		return criteria;
	}

	/**
	 * Set the criteria for the lookup.
	 *
	 * @param criteria The criteria to set.
	 */
	public void setCriteria(TemplateProductPlanCriteria criteria)
	{
		this.criteria = criteria;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TemplateProductPlanInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString()
				+ "]";
	}
}
