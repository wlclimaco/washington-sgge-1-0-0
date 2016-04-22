package com.qat.samples.sysmgmt.nf.model.request;

import com.qat.samples.sysmgmt.nf.model.criteria.OrcamentoCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class OrcamentoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private OrcamentoCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public OrcamentoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new OrcamentoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(OrcamentoCriteria criteria)
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
		return "OrcamentoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
