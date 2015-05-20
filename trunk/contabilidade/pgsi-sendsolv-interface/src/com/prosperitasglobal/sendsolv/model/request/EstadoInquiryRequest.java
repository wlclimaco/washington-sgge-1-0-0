package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.EstadoCriteria;

/**
 * The Class ProdutoInquiryRequest.
 */
public class EstadoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private EstadoCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public EstadoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new EstadoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(EstadoCriteria criteria)
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
		return "EstadoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
