package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.criteria.DepositoCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class DepositoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private DepositoCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public DepositoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new DepositoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(DepositoCriteria criteria)
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
		return "DepositoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
