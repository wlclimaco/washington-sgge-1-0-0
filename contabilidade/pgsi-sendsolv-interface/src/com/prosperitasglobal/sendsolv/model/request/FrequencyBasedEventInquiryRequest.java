package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.FrequencyBasedEventCriteria;

/**
 * The frequency based event inquiry request is used to do inquiry lookups on the data store, using the criteria
 * specified in the <code>criteria</code> property.
 */
public class FrequencyBasedEventInquiryRequest extends PagedInquiryRequest
{
	/** The criteria to use in the inquiry request. */
	private FrequencyBasedEventCriteria criteria;

	/**
	 * Get the criteria for the lookup.
	 *
	 * @return The criteria.
	 */
	public FrequencyBasedEventCriteria getCriteria()
	{
		if (null == criteria)
		{
			criteria = new FrequencyBasedEventCriteria();
		}

		return criteria;
	}

	/**
	 * Set the criteria for the lookup.
	 *
	 * @param criteria The criteria to set.
	 */
	public void setCriteria(FrequencyBasedEventCriteria criteria)
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
		return "FrequencyBasedEventInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString()
				+ "]";
	}
}
