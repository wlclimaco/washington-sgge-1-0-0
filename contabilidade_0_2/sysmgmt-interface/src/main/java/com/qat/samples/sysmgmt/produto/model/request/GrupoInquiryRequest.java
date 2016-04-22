package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.criteria.GrupoCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class GrupoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private GrupoCriteria criteria;

	/**
	 * Gets the criteria.
	 * 
	 * @return the criteria
	 */
	public GrupoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new GrupoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 * 
	 * @param criteria the criteria
	 */
	public void setCriteria(GrupoCriteria criteria)
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
		return "GrupoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
