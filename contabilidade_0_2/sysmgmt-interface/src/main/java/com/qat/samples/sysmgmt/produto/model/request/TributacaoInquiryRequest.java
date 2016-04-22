package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.criteria.TributacaoCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class TributacaoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private TributacaoCriteria criteria;

	/**
	 * Gets the criteria.
	 * 
	 * @return the criteria
	 */
	public TributacaoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new TributacaoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 * 
	 * @param criteria the criteria
	 */
	public void setCriteria(TributacaoCriteria criteria)
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
		return "TributacaoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
