package com.qat.samples.sysmgmt.clinica.model.request;

import com.qat.samples.sysmgmt.util.model.criteria.ComumCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class EspecializacaoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private ComumCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public ComumCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new ComumCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(ComumCriteria criteria)
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
		return "EspecializacaoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString()
				+ "]";
	}

}
