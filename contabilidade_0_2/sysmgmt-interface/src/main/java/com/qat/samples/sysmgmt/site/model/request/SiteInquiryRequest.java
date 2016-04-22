package com.qat.samples.sysmgmt.site.model.request;

import com.qat.samples.sysmgmt.site.model.criteria.SiteCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class SiteInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private SiteCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public SiteCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new SiteCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(SiteCriteria criteria)
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
		return "BancoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
