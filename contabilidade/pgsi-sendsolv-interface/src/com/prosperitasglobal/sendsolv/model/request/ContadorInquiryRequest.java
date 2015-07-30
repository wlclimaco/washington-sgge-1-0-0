package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.ContadorCriteria;

/**
 * The Class ProdutoInquiryRequest.
 */
public class ContadorInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private ContadorCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public ContadorCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new ContadorCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(ContadorCriteria criteria)
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
		return "ContadorInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
