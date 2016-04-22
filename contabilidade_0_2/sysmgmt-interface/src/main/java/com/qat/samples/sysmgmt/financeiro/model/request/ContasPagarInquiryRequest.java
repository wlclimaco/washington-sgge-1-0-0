package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.samples.sysmgmt.financeiro.model.criteria.FinanceiroCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class FinanceiroInquiryRequest.
 */
public class ContasPagarInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private FinanceiroCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public FinanceiroCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new FinanceiroCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(FinanceiroCriteria criteria)
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
		return "FinanceiroInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
