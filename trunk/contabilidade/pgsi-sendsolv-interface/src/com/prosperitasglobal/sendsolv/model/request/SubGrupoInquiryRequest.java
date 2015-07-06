package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.SubGrupoCriteria;

/**
 * The Class ProdutoInquiryRequest.
 */
public class SubGrupoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private SubGrupoCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public SubGrupoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new SubGrupoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(SubGrupoCriteria criteria)
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
		return "SubGrupoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
