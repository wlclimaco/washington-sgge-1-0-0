package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.MoneyTransferBatchCriteria;

/**
 * The money transfer batch inquiry request is used to do inquiry lookups on the data store, using the criteria
 * specified in the <code>criteria</code> property.
 */
public class MoneyTransferBatchInquiryRequest extends PagedInquiryRequest
{
	/** The criteria to use in the inquiry request. */
	private MoneyTransferBatchCriteria criteria;

	/**
	 * Instantiates a new money transfer batch inquiry request.
	 */
	public MoneyTransferBatchInquiryRequest()
	{
		super();
	}

	/**
	 * Instantiates a new money transfer batch inquiry request.
	 *
	 * @param criteria the criteria
	 */
	public MoneyTransferBatchInquiryRequest(MoneyTransferBatchCriteria criteria)
	{

		this.criteria = criteria;
	}

	/**
	 * Get the criteria for the lookup.
	 *
	 * @return The criteria.
	 */
	public MoneyTransferBatchCriteria getCriteria()
	{
		if (null == criteria)
		{
			criteria = new MoneyTransferBatchCriteria();
		}

		return criteria;
	}

	/**
	 * Set the criteria for the lookup.
	 *
	 * @param criteria The criteria to set.
	 */
	public void setCriteria(MoneyTransferBatchCriteria criteria)
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
		return "MoneyTransferBatchInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString()
				+ "]";
	}
}
