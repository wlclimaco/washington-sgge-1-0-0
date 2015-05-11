package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.FuncionarioCriteria;

/**
 * The Class ProdutoInquiryRequest.
 */
public class FuncionarioInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private FuncionarioCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public FuncionarioCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new FuncionarioCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(FuncionarioCriteria criteria)
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
		return "FuncionarioInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
