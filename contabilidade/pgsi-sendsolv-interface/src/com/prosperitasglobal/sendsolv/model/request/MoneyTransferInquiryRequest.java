package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.MoneyTransferCriteria;

/**
 * The money transfer inquiry request is used to do inquiry lookups on the data store, using the criteria specified in
 * the <code>criteria</code> property.
 */
public class MoneyTransferInquiryRequest extends PagedInquiryRequest
{
	/** The criteria to use in the inquiry request. */
	private MoneyTransferCriteria criteria;

	/**
	 * Instantiates a new money transfer inquiry request.
	 */
	public MoneyTransferInquiryRequest()
	{
		super();
	}

	/**
	 * Instantiates a new money transfer inquiry request.
	 *
	 * @param criteria the criteria
	 */
	public MoneyTransferInquiryRequest(MoneyTransferCriteria criteria)
	{
		super();
		this.criteria = criteria;
	}

	/**
	 * Get the criteria for the lookup.
	 *
	 * @return The criteria.
	 */
	public MoneyTransferCriteria getCriteria()
	{
		if (null == criteria)
		{
			criteria = new MoneyTransferCriteria();
		}

		return criteria;
	}

	/**
	 * Set the criteria for the lookup.
	 *
	 * @param criteria The criteria to set.
	 */
	public void setCriteria(MoneyTransferCriteria criteria)
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
		return "MoneyTransferInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}
}
