package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.MarcaCriteria;

/**
 * The Class ProdutoInquiryRequest.
 */
public class MarcaInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private MarcaCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public MarcaCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new MarcaCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(MarcaCriteria criteria)
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
		return "MarcaInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
