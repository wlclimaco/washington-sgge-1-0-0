package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.CidadeCriteria;

/**
 * The Class ProdutoInquiryRequest.
 */
public class CidadeInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private CidadeCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public CidadeCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new CidadeCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(CidadeCriteria criteria)
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
