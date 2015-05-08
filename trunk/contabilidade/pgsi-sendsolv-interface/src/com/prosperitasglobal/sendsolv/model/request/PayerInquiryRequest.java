package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.PayerCriteria;

/**
 * The payer inquiry request is used to do inquiry lookups on the data store, using the criteria specified in the
 * <code>criteria</code> property.
 */
public class PayerInquiryRequest extends PagedInquiryRequest
{
	/** The criteria to use in the inquiry request. */
	private PayerCriteria criteria;

	/**
	 * Get the criteria for the lookup.
	 *
	 * @return The criteria.
	 */
	public PayerCriteria getCriteria()
	{
		if (null == criteria)
		{
			criteria = new PayerCriteria();
		}

		return criteria;
	}

	/**
	 * Set the criteria for the lookup.
	 *
	 * @param criteria The criteria to set.
	 */
	public void setCriteria(PayerCriteria criteria)
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
		return "PayerInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}
}
