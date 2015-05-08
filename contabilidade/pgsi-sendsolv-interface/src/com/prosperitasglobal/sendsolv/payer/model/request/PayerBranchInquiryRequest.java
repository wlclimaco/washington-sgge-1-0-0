package com.prosperitasglobal.sendsolv.payer.model.request;

import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.payer.model.request.criteria.PayerBranchCriteria;

/**
 * The payer branch inquiry request is used to do inquiry lookups on the data store, using the criteria
 * specified in
 * the <code>criteria</code> property.
 */
public class PayerBranchInquiryRequest extends PagedInquiryRequest
{
	/** The criteria to use in the inquiry request. */
	private PayerBranchCriteria criteria;

	/**
	 * Get the criteria for the lookup.
	 *
	 * @return The criteria.
	 */
	public PayerBranchCriteria getCriteria()
	{
		if (null == criteria)
		{
			criteria = new PayerBranchCriteria();
		}

		return criteria;
	}

	/**
	 * Set the criteria for the lookup.
	 *
	 * @param criteria The criteria to set.
	 */
	public void setCriteria(PayerBranchCriteria criteria)
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
		return "PayerBranchInquiryRequest [getCriteria()=" + getCriteria() + ", toString()="
				+ super.toString() + "]";
	}
}
