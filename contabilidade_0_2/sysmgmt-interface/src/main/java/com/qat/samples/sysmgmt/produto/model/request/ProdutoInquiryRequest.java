package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.criteria.ProdutoCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class ProdutoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private ProdutoCriteria criteria;

	private String ncm;


	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public ProdutoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new ProdutoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(ProdutoCriteria criteria)
	{
		this.criteria = criteria;
	}


	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	@Override
	public String toString() {
		return "ProdutoInquiryRequest [getCriteria()=" + getCriteria() + ", getNcm()=" + getNcm() + ", toString()="
				+ super.toString() + "]";
	}

}
