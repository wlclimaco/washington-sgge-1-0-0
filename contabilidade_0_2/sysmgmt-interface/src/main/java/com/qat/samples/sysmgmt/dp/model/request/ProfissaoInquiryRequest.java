package com.qat.samples.sysmgmt.dp.model.request;

import com.qat.samples.sysmgmt.dp.model.criteria.ProfissaoCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class ProfissaoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private ProfissaoCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public ProfissaoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new ProfissaoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(ProfissaoCriteria criteria)
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
		return "ProfissaoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
