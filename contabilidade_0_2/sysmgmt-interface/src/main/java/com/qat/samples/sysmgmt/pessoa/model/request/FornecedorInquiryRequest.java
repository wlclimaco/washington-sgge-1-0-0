package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.samples.sysmgmt.pessoa.model.criteria.FornecedorCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class FornecedorInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private FornecedorCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public FornecedorCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new FornecedorCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(FornecedorCriteria criteria)
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
		return "FornecedorInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
