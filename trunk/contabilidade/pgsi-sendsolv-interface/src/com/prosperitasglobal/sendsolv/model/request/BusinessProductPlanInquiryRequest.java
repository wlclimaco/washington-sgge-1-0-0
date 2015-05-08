package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.BusinessProductPlanCriteria;

/**
 * The business product plan inquiry request is used to do inquiry lookups on the data store, using the criteria
 * specified in the <code>criteria</code> property.
 */
public class BusinessProductPlanInquiryRequest extends PagedInquiryRequest
{
	/** The criteria to use in the inquiry request. */
	private BusinessProductPlanCriteria criteria;

	/**
	 * Get the criteria for the lookup.
	 *
	 * @return The criteria.
	 */
	public BusinessProductPlanCriteria getCriteria()
	{
		if (null == criteria)
		{
			criteria = new BusinessProductPlanCriteria();
		}

		return criteria;
	}

	/**
	 * Set the criteria for the lookup.
	 *
	 * @param criteria The criteria to set.
	 */
	public void setCriteria(BusinessProductPlanCriteria criteria)
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
		return "BusinessProductPlanInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString()
				+ "]";
	}
}
