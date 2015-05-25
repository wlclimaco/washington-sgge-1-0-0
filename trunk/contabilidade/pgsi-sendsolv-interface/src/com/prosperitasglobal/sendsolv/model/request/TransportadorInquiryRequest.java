package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.TransportadorCriteria;

/**
 * The Class ProdutoInquiryRequest.
 */
public class TransportadorInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private TransportadorCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public TransportadorCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new TransportadorCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(TransportadorCriteria criteria)
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
		return "TransportadorInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
