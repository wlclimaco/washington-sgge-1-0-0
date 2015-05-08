package com.prosperitasglobal.sendsolv.payer.model.request;

import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.payer.model.request.criteria.PayerStateProvinceRegionCriteria;

/**
 * The payer state province region inquiry request is used to do inquiry lookups on the data store, using the criteria
 * specified in
 * the <code>criteria</code> property.
 */
public class PayerStateProvinceRegionInquiryRequest extends PagedInquiryRequest
{
	/** The criteria to use in the inquiry request. */
	private PayerStateProvinceRegionCriteria criteria;

	/**
	 * Get the criteria for the lookup.
	 *
	 * @return The criteria.
	 */
	public PayerStateProvinceRegionCriteria getCriteria()
	{
		if (null == criteria)
		{
			criteria = new PayerStateProvinceRegionCriteria();
		}

		return criteria;
	}

	/**
	 * Set the criteria for the lookup.
	 *
	 * @param criteria The criteria to set.
	 */
	public void setCriteria(PayerStateProvinceRegionCriteria criteria)
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
		return "PayerStateProvinceRegionInquiryRequest [getCriteria()=" + getCriteria() + ", toString()="
				+ super.toString() + "]";
	}
}
