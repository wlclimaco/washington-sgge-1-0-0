package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.DailyCurrencyRateCriteria;

/**
 * The daily currency rate inquiry request is used to do inquiry lookups on the data store, using the criteria specified
 * in the <code>criteria</code> property.
 */
public class DailyCurrencyRateInquiryRequest extends PagedInquiryRequest
{
	/** The criteria to use in the inquiry request. */
	private DailyCurrencyRateCriteria criteria;

	/**
	 * Get the criteria for the lookup.
	 *
	 * @return The criteria.
	 */
	public DailyCurrencyRateCriteria getCriteria()
	{
		if (null == criteria)
		{
			criteria = new DailyCurrencyRateCriteria();
		}

		return criteria;
	}

	/**
	 * Set the criteria for the lookup.
	 *
	 * @param criteria The criteria to set.
	 */
	public void setCriteria(DailyCurrencyRateCriteria criteria)
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
		return "DailyCurrencyRateInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString()
				+ "]";
	}
}
