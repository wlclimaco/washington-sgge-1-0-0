package com.qat.samples.sysmgmt.contato.model.request;

import com.qat.samples.sysmgmt.contato.model.criteria.ContatoCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class ContatoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private ContatoCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public ContatoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new ContatoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(ContatoCriteria criteria)
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
		return "ContatoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
