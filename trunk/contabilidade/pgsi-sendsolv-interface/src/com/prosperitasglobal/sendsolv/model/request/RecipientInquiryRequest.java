package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.RecipientCriteria;

/**
 * The Class RecipientInquiryRequest.
 */
public class RecipientInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private RecipientCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public RecipientCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new RecipientCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(RecipientCriteria criteria)
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
		return "RecipientInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}
}
