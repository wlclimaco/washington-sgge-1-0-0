package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.PlanoCriteria;

/**
 * The Class ProdutoInquiryRequest.
 */
public class PlanoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private PlanoCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public PlanoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new PlanoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(PlanoCriteria criteria)
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
		return "PlanoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
