package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.AlertasCriteria;

/**
 * The Class ProdutoInquiryRequest.
 */
public class AlertasInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private AlertasCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public AlertasCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new AlertasCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(AlertasCriteria criteria)
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
		return "AlertasInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
