package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.TabelaCriteria;

/**
 * The Class TabelaInquiryRequest.
 */
public class TabelaInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private TabelaCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public TabelaCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new TabelaCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(TabelaCriteria criteria)
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
		return "TabelaInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
