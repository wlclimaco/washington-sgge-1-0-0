package com.qat.samples.sysmgmt.nf.model.request;

import com.qat.samples.sysmgmt.arquivo.model.criteria.ArquivoCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class ContasInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private ArquivoCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public ArquivoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new ArquivoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(ArquivoCriteria criteria)
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
