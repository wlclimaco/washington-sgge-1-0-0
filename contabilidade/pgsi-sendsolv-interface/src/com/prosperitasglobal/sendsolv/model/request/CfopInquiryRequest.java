package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Cfop;

/**
 * The Class ProdutoInquiryRequest.
 */
public class CfopInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private Cfop criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public Cfop getCriteria()
	{
		if (criteria == null)
		{
			criteria = new Cfop();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(Cfop criteria)
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
		return "ArquivoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
