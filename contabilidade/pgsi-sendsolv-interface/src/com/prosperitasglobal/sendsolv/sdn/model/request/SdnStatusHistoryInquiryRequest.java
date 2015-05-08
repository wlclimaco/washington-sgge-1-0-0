package com.prosperitasglobal.sendsolv.sdn.model.request;

import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.criteria.SdnStatusHistoryCriteria;

/**
 * The money transfer inquiry request is used to do inquiry lookups on the data store, using the criteria specified in
 * the <code>criteria</code> property.
 */
public class SdnStatusHistoryInquiryRequest extends PagedInquiryRequest
{
	/** The criteria to use in the inquiry request. */
	private SdnStatusHistoryCriteria criteria;

	/**
	 * Get the criteria for the lookup.
	 *
	 * @return The criteria.
	 */
	public SdnStatusHistoryCriteria getCriteria()
	{
		if (null == criteria)
		{
			criteria = new SdnStatusHistoryCriteria();
		}

		return criteria;
	}

	/**
	 * Set the criteria for the lookup.
	 *
	 * @param criteria The criteria to set.
	 */
	public void setCriteria(SdnStatusHistoryCriteria criteria)
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
		return "SdnStatusHistoryInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString()
				+ "]";
	}
}
